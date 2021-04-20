package com.electric.henryElect.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dni;
    private Address address;
    private String name;
    private String lastName;

    @ElementCollection()
    private List<LightMeter> lightMeter;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "Invoices")
    private Invoice invoice;

}
