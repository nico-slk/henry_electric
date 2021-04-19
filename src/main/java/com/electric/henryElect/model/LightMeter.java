package com.electric.henryElect.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode()
public class LightMeter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String model;
    private String brand;

    private Integer initialMedition; //Medición inicial
    private Integer finalMedition; //Medición final
    private Integer totalConsumption; //Consumo total en Kwh

}
