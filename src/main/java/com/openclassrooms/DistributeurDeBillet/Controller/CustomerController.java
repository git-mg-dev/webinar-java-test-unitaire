package com.openclassrooms.DistributeurDeBillet.Controller;

import com.openclassrooms.DistributeurDeBillet.Entity.Customer;
import com.openclassrooms.DistributeurDeBillet.Entity.DTO.CustomerDepositDTO;
import com.openclassrooms.DistributeurDeBillet.Entity.DTO.CustomerWithdrawDTO;
import com.openclassrooms.DistributeurDeBillet.Exception.DepositException;
import com.openclassrooms.DistributeurDeBillet.Service.CustomerService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/deposit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Object> deposit(@RequestBody CustomerDepositDTO customerDepositDTO){
        try{
            return ResponseEntity.ok(customerService.deposit(customerDepositDTO));
        }catch(NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }catch(DepositException e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Invalid deposit value");
        }
    }

    @PostMapping("/withdraw")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Object> withdraw(@RequestBody CustomerWithdrawDTO customerWithdrawDTO){
        try{
            return ResponseEntity.ok(customerService.withdraw(customerWithdrawDTO));
        }catch(DepositException | NotFoundException e){
            return null;
        }
    }
}
