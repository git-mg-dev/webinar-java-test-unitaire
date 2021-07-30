package com.openclassrooms.DistributeurDeBillet.Service;

import com.openclassrooms.DistributeurDeBillet.Entity.Customer;
import com.openclassrooms.DistributeurDeBillet.Entity.DTO.CustomerDTO;
import com.openclassrooms.DistributeurDeBillet.Entity.DTO.CustomerDepositDTO;
import com.openclassrooms.DistributeurDeBillet.Entity.DTO.CustomerWithdrawDTO;
import com.openclassrooms.DistributeurDeBillet.Exception.DepositException;
import javassist.NotFoundException;

import java.util.List;

public interface CustomerService {
    public CustomerDTO deposit(CustomerDepositDTO customerDTO) throws DepositException, NotFoundException;
    public Iterable<Customer> getAll();
    public CustomerDTO withdraw(CustomerWithdrawDTO customerWithdrawDTO) throws DepositException, NotFoundException;
}
