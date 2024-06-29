package com.alpha.pointage.service;

import com.alpha.pointage.entity.PrimeEntity;
import com.alpha.pointage.repository.PrimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PrimeService {

    private final PrimeRepository primeRepository;


    public PrimeService(PrimeRepository primeRepository) {
        this.primeRepository = primeRepository;
    }
    public PrimeEntity addPrime(PrimeEntity prime){
        primeRepository.save(prime);
        return prime;
    }

    public List<PrimeEntity> findAllPrime(){
        return primeRepository.findAll();
    }

}
