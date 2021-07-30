package com.openclassrooms.DistributeurDeBillet.Entity.DTO;

import lombok.Data;

@Data
public class CustomerDepositDTO {
    private String name;
    private String firstName;
    private Integer depositValue;
}
