package com.openclassrooms.DistributeurDeBillet.Entity.DTO;

import lombok.Data;

import javax.persistence.Entity;

@Data
public class CustomerDTO {
    private String name;
    private String firstName;
    private Integer accountBalance;
}
