package com.openclassrooms.DistributeurDeBillet.Entity.DTO;

import lombok.Data;

@Data
public class CustomerWithdrawDTO {
    private String name;
    private String firstName;
    private Integer withdrawValue;
    private String automatIdentifier;
}
