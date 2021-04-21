package com.electric.henryElect.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer dni;
    private String name;
    private String lastName;

    @ElementCollection()
    private List<LightMeter> lightMeter;

    @OneToMany(targetEntity=Invoice.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "Invoices")
    private List<Invoice> invoice;

    @OneToOne(targetEntity=Address.class, fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "address")
    private Address address;

}
