package com.electric.henryElect.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LightMeter lightMeter; // Numero de medidor
    private String client; // Cliente
    private String address; //Domicilio
    private Integer initialMedition; //Medición inicial
    private Integer finalMedition; //Medición final
    private Integer totalConsumption; //Consumo total en Kwh
    private LocalDate timeInitialMedition; //Fecha y hora medición inicial
    private LocalDate timeFinalMedition; //Fecha y hora medición final
    private Double rate; //Tipo de tarifa
    private Double total = totalConsumption * rate; //Total a pagar (Consumo * Tarifa)
    private Boolean pay;

}
