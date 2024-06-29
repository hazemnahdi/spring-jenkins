package com.alpha.pointage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "primes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String libelle;
    @JsonIgnoreProperties("employees")
    @ManyToOne(fetch = FetchType.EAGER)
    private EmployeeEntity employee;
    private Double montant;
    @Temporal(TemporalType.DATE)
    private Date date;

}
