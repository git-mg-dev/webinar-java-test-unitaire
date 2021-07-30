package com.openclassrooms.DistributeurDeBillet.Repository;

import com.openclassrooms.DistributeurDeBillet.Entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> findOneByNameAndFirstName(String name, String firstName);
}
