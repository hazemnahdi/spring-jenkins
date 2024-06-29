package com.alpha.pointage.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Table(name = "employees")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long salaire;
    @JsonIgnoreProperties("employees")
    @JsonBackReference(value="employee-prime")
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<PrimeEntity> primes;

    @JsonIgnoreProperties("employees")
    @JsonBackReference(value="employee-avance")
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<AvanceEntity> avances;

}
