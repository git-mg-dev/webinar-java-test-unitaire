package com.openclassrooms.DistributeurDeBillet.Entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long customerId;
    @NotNull
    private String name;
    @NotNull
    private String firstName;
    @NotNull
    private Integer accountBalance;

}
