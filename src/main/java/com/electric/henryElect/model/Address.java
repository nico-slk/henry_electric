package com.electric.henryElect.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String street;
    private String number;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "light_meter")
    private LightMeter lightMeter;

}
