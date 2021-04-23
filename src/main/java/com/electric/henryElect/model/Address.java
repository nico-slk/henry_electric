package com.electric.henryElect.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String number;

    @OneToOne(targetEntity=LightMeter.class, fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "lightMeterId")
    private LightMeter lightMeter;

}
