package com.openclassrooms.DistributeurDeBillet;

import com.openclassrooms.DistributeurDeBillet.Entity.Customer;
import com.openclassrooms.DistributeurDeBillet.Entity.DTO.CustomerDTO;
import com.openclassrooms.DistributeurDeBillet.Entity.DTO.CustomerDepositDTO;
import com.openclassrooms.DistributeurDeBillet.Entity.DTO.CustomerWithdrawDTO;
import com.openclassrooms.DistributeurDeBillet.Entity.Distributeur;
import com.openclassrooms.DistributeurDeBillet.Exception.DepositException;
import com.openclassrooms.DistributeurDeBillet.Repository.CustomerRepository;
import com.openclassrooms.DistributeurDeBillet.Repository.DistributeurRepository;
import com.openclassrooms.DistributeurDeBillet.Service.CustomerServiceImpl;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock DistributeurRepository distributeurRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer;
    private Distributeur distributeur;
    private CustomerWithdrawDTO customerWithdrawDTO;
    private CustomerDepositDTO customerDepositDTO;

    @BeforeEach
    public void init() {
        this.customer = new Customer();
        customer.setFirstName("Alex");
        customer.setName("Jean");

        this.distributeur = new Distributeur();
        distributeur.setId(123L);
        distributeur.setAutomatIdentifier("Automate-123");

        this.customerWithdrawDTO = new CustomerWithdrawDTO();
        customerWithdrawDTO.setName("Marcel");
        customerWithdrawDTO.setFirstName("Alex");
        customerWithdrawDTO.setAutomatIdentifier("Automate-123");

        this.customerDepositDTO = new CustomerDepositDTO();
        customerDepositDTO.setFirstName("Alex");
        customerDepositDTO.setName("Jean");
    }

    @Test
    public void withdrawNotEnoughMoneyInTheATM(){
        // GIVEN
        customer.setAccountBalance(150);
        customerWithdrawDTO.setWithdrawValue(100);
        distributeur.setQuantityMoneyAvailable(50);

        Optional<Distributeur> optionalDistributeur = Optional.of(distributeur);
        Optional<Customer> optionalCustomer = Optional.of(customer);

        when(customerRepository.findOneByNameAndFirstName(any(String.class), any(String.class))).thenReturn(optionalCustomer);
        when(distributeurRepository.findByAutomatIdentifier(any(String.class))).thenReturn(optionalDistributeur);

        // WHEN & THEN
        assertThrows(DepositException.class, () -> customerService.withdraw(customerWithdrawDTO));
    }

    @Test
    public void withdrawNotEnoughMoneyInBankAccount(){
        // GIVEN
        customer.setAccountBalance(10);
        customerWithdrawDTO.setWithdrawValue(1500);
        distributeur.setQuantityMoneyAvailable(1000);

        Optional<Distributeur> optionalDistributeur = Optional.of(distributeur);
        Optional<Customer> optionalCustomer = Optional.of(customer);

        when(customerRepository.findOneByNameAndFirstName(any(String.class), any(String.class))).thenReturn(optionalCustomer);
        when(distributeurRepository.findByAutomatIdentifier(any(String.class))).thenReturn(optionalDistributeur);

        // WHEN & THEN
        assertThrows(DepositException.class, () -> customerService.withdraw(customerWithdrawDTO));
    }

    @Test
    public void userNotFound() {
        // GIVEN
        customer.setAccountBalance(150);
        customerWithdrawDTO.setWithdrawValue(50);
        distributeur.setQuantityMoneyAvailable(1000);

        Optional<Distributeur> optionalDistributeur = Optional.of(distributeur);
        Optional<Customer> optionalCustomer = Optional.empty(); // will provoke notFoundException

        when(customerRepository.findOneByNameAndFirstName(any(String.class), any(String.class))).thenReturn(optionalCustomer);
        when(distributeurRepository.findByAutomatIdentifier(any(String.class))).thenReturn(optionalDistributeur);
        when(distributeurRepository.save(any())).thenReturn(any());

        // WHEN & THEN
        assertThrows(NotFoundException.class, () -> customerService.withdraw(customerWithdrawDTO));
    }

    @Test
    public void userFound() throws NotFoundException, DepositException {
        // GIVEN
        customer.setAccountBalance(150);
        customerWithdrawDTO.setWithdrawValue(50);
        customerWithdrawDTO.setName("Jean");
        distributeur.setQuantityMoneyAvailable(1000);

        Optional<Distributeur> optionalDistributeur = Optional.of(distributeur);
        Optional<Customer> optionalCustomer = Optional.of(customer);

        when(customerRepository.findOneByNameAndFirstName(any(String.class), any(String.class))).thenReturn(optionalCustomer);
        when(distributeurRepository.findByAutomatIdentifier(any(String.class))).thenReturn(optionalDistributeur);
        when(distributeurRepository.save(any())).thenReturn(any());

        // WHEN
        CustomerDTO result = customerService.withdraw(customerWithdrawDTO);

        // THEN
        assertEquals(customer.getName(), result.getName());
    }

    @Test
    public void withdrawTest()throws NotFoundException, DepositException {
        // GIVEN
        customer.setAccountBalance(150);
        customerWithdrawDTO.setWithdrawValue(50);
        distributeur.setQuantityMoneyAvailable(1000);

        Optional<Distributeur> optionalDistributeur = Optional.of(distributeur);
        Optional<Customer> optionalCustomer = Optional.of(customer);

        when(customerRepository.findOneByNameAndFirstName(any(String.class), any(String.class))).thenReturn(optionalCustomer);
        when(distributeurRepository.findByAutomatIdentifier(any(String.class))).thenReturn(optionalDistributeur);
        when(distributeurRepository.save(any())).thenReturn(any());

        // WHEN
        CustomerDTO result = customerService.withdraw(customerWithdrawDTO);

        // THEN
        assertEquals(customer.getName(), result.getName());
    }

    @Test
    public void depositTest() throws NotFoundException, DepositException {
        // GIVEN
        customer.setAccountBalance(150);
        distributeur.setQuantityMoneyAvailable(1000);
        customerDepositDTO.setDepositValue(100);

        when(customerRepository.findOneByNameAndFirstName(any(), any())).thenReturn(Optional.of(customer));
        when(customerRepository.save(any())).thenReturn(customer);

        // WHEN
        CustomerDTO result = customerService.deposit(customerDepositDTO);

        // THEN
        assertEquals(customerDepositDTO.getFirstName(), result.getFirstName());
    }
}
