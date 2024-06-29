package com.alpha.pointage.dto;

import com.alpha.pointage.entity.PrimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;
    private String name;
    private Long salaire;
    private List<PrimeDTO> primes;
    private List<AvanceDTO> avances;
    private Double totalPrimes;
    private Double totalPrimesWithDate;
    private Double totalAvanceWithMois;

    public void calculateTotalPrimes() {
        if (primes != null && !primes.isEmpty()) {
            this.totalPrimes = primes.stream()
                    .filter(Objects::nonNull) // Filtrer les objets null
                    .mapToDouble(prime -> prime != null && prime.getMontant() != null ? prime.getMontant() : 0.0) // Utiliser seulement les montants non null, traiter les objets null
                    .sum(); // Somme des montants
        } else {
            this.totalPrimes = 0.0; // Si la liste de primes est vide ou nulle, initialiser à zéro
        }
    }
    public void calculateTotalPrimesWithDate() {
        if (primes != null && !primes.isEmpty()) {
            this.totalPrimesWithDate = IntStream.range(0, primes.size() - 1) // Créer un flux d'indices de 0 à la taille - 1
                    .mapToDouble(index -> {
                        PrimeDTO currentPrime = primes.get(index);
                        PrimeDTO nextPrime = primes.get(index + 1);
                        int currentYear = currentPrime.getDate().getYear(); // Supposons que getDate() renvoie l'objet java.time.LocalDate représentant la date de la prime
                        int nextYear = nextPrime.getDate().getYear();
                        // Comparer les années des dates actuelle et suivante, et retourner le montant si l'année est différente
                        return currentYear == nextYear ? currentPrime.getMontant()+nextPrime.getMontant() : 0.0;
                    })
                    .sum(); // Somme des montants
        } else {
            this.totalPrimesWithDate = 0.0; // Si la liste de primes est vide ou nulle, initialiser à zéro
        }
    }

}
