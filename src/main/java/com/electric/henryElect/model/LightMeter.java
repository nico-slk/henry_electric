package com.electric.henryElect.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class LightMeter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String model;
    private String brand;
    private Double initialMedition; //Medición inicial
    private Double finalMedition; //Medición final
    private Double totalConsumption; //Consumo total en Kwh


}
