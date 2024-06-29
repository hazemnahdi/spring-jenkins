package com.alpha.pointage.controller;

import com.alpha.pointage.entity.PrimeEntity;
import com.alpha.pointage.service.PrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("prime")
public class PrimeController {
    private final PrimeService primeService;


    public PrimeController(PrimeService primeService) {
        this.primeService = primeService;
    }
    @PostMapping("/add")
    public ResponseEntity<PrimeEntity> addPrime(@RequestBody PrimeEntity primeEntity){
        PrimeEntity primeCreated =primeService.addPrime(primeEntity);
        return ResponseEntity.ok(primeCreated);
    }
    @GetMapping("/primes")
    public ResponseEntity<List<PrimeEntity>> getPrimes(){
        return ResponseEntity.ok(primeService.findAllPrime());
    }
}
