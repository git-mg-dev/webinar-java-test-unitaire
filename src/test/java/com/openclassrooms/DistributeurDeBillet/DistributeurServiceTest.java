package com.openclassrooms.DistributeurDeBillet;

import com.openclassrooms.DistributeurDeBillet.Entity.DTO.DistributeurDTO;
import com.openclassrooms.DistributeurDeBillet.Entity.DTO.DistributeurRefillDTO;
import com.openclassrooms.DistributeurDeBillet.Entity.Distributeur;
import com.openclassrooms.DistributeurDeBillet.Exception.DepositException;
import com.openclassrooms.DistributeurDeBillet.Repository.DistributeurRepository;
import com.openclassrooms.DistributeurDeBillet.Service.DistributeurServiceImpl;
import javassist.NotFoundException;
import org.junit.jupiter.api.*;
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
public class DistributeurServiceTest {
    @Mock
    private DistributeurRepository distributeurRepository;
    @InjectMocks
    private DistributeurServiceImpl distributeurService;
    private DistributeurRefillDTO distributeurRefillDTO;
    private Distributeur distributeur;

    @BeforeEach
    public void init(){
        this.distributeurRefillDTO = new DistributeurRefillDTO();
        distributeurRefillDTO.setAutomatIdentifier("ABC");

        this.distributeur = new Distributeur();
        distributeur.setQuantityMoneyAvailable(100);
        distributeur.setAutomatIdentifier("ABC");
    }

    @Test
    public void refill() throws NotFoundException, DepositException{
        // GIVEN
        distributeurRefillDTO.setRefillValue(0);
        Optional<Distributeur> optionalDistributeur = Optional.of(distributeur);

        when(distributeurRepository.findByAutomatIdentifier(any())).thenReturn(optionalDistributeur);
        when(distributeurRepository.save(any())).thenReturn(any());

        // WHEN
        DistributeurDTO result =  distributeurService.refill(distributeurRefillDTO); //.getAutomatIdentifier()

        // THEN
        assertEquals("ABC", result.getAutomatIdentifier());
    }

    @Test
    public void refillFails() {
        // GIVEN
        distributeurRefillDTO.setRefillValue(100);
        Optional<Distributeur> optionalDistributeur = Optional.empty();

        when(distributeurRepository.findByAutomatIdentifier(any())).thenReturn(optionalDistributeur);
        //when(distributeurRepository.save(any())).thenReturn(any()); // useless because NotFoundException is thrown before save is called

        // WHEN & THEN
        assertThrows(DepositException.class, () -> distributeurService.refill(distributeurRefillDTO));
    }
}
