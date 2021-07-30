package com.openclassrooms.DistributeurDeBillet.Service;

import com.openclassrooms.DistributeurDeBillet.Entity.DTO.CustomerDTO;
import com.openclassrooms.DistributeurDeBillet.Entity.DTO.CustomerDepositDTO;
import com.openclassrooms.DistributeurDeBillet.Entity.DTO.DistributeurDTO;
import com.openclassrooms.DistributeurDeBillet.Entity.DTO.DistributeurRefillDTO;
import com.openclassrooms.DistributeurDeBillet.Entity.Distributeur;
import com.openclassrooms.DistributeurDeBillet.Exception.DepositException;
import com.openclassrooms.DistributeurDeBillet.Repository.DistributeurRepository;
import javassist.NotFoundException;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class DistributeurServiceImpl implements DistributeurService{

    private final DistributeurRepository distributeurRepository;

    @Override
    public DistributeurDTO refill(DistributeurRefillDTO distributeurRefillDTO) throws DepositException, NotFoundException {
        validateDeposit(distributeurRefillDTO.getRefillValue());
        Distributeur distributeur = getDisributeurByAutomateIdentifier(distributeurRefillDTO.getAutomatIdentifier());
        distributeur.setQuantityMoneyAvailable(distributeur.getQuantityMoneyAvailable() + distributeurRefillDTO.getRefillValue());
        distributeurRepository.save(distributeur);
        DistributeurDTO ret = new DistributeurDTO();
        ret.setQuantityMoneyAvailable(distributeur.getQuantityMoneyAvailable());
        ret.setAutomatIdentifier(distributeur.getAutomatIdentifier());
        return ret;
    }

    private void validateDeposit(Integer value) throws DepositException {
        if (value / 10 != 0){
            throw new DepositException();
        }
    }

    private Distributeur getDisributeurByAutomateIdentifier(String automateIdentifier) throws NotFoundException {
        Optional<Distributeur> optionalDistributeur = distributeurRepository.findByAutomatIdentifier(automateIdentifier);
        if (optionalDistributeur.isEmpty()){
            throw new NotFoundException("Invalid identifier for automate");
        }
        return optionalDistributeur.get();
    }
}
