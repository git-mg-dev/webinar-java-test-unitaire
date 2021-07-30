package com.openclassrooms.DistributeurDeBillet.Repository;

import com.openclassrooms.DistributeurDeBillet.Entity.Distributeur;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DistributeurRepository extends CrudRepository<Distributeur, Integer> {
    Optional<Distributeur> findByAutomatIdentifier(String automatIdentifier);
}
