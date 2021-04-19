package com.electric.henryElect.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Invoice{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate timeInitialMedition; //Fecha y hora medición inicial
    private LocalDate timeFinalMedition; //Fecha y hora medición final
    private Double rate; //Tipo de tarifa
    private Double total; //Total a pagar (Consumo * Tarifa)
    private Boolean pay;

}
