package com.openclassrooms.DistributeurDeBillet.Entity.DTO;

import lombok.Data;

@Data
public class DistributeurDTO {
    private Integer quantityMoneyAvailable;
    private String automatIdentifier;
}
