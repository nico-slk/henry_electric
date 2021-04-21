package com.electric.henryElect.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @OneToOne(targetEntity=Address.class, fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "address")
    private Address address;

}
