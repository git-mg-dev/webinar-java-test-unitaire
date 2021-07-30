package com.openclassrooms.DistributeurDeBillet.Service;

import com.openclassrooms.DistributeurDeBillet.Entity.DTO.CustomerDTO;
import com.openclassrooms.DistributeurDeBillet.Entity.DTO.CustomerDepositDTO;
import com.openclassrooms.DistributeurDeBillet.Entity.DTO.DistributeurDTO;
import com.openclassrooms.DistributeurDeBillet.Entity.DTO.DistributeurRefillDTO;
import com.openclassrooms.DistributeurDeBillet.Exception.DepositException;
import javassist.NotFoundException;

import java.util.List;

public interface DistributeurService {
    public DistributeurDTO refill(DistributeurRefillDTO distributeurRefillDTO) throws DepositException, NotFoundException;
}
