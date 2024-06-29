package com.alpha.pointage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvanceDTO {

    private Long id;
    private String libelle;
    private Double montant;
    private Date date;
}
