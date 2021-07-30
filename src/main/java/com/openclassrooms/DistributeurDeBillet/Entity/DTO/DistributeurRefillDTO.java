package com.openclassrooms.DistributeurDeBillet.Entity.DTO;

import lombok.Data;

@Data
public class DistributeurRefillDTO {
    private String automatIdentifier;
    private Integer refillValue;
}
