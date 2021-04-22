package com.electric.henryElect.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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

//    private Integer lightMeterId;

//    @OneToOne(targetEntity=LightMeter.class, fetch = FetchType.EAGER, cascade=CascadeType.ALL)
//    @JoinColumn(name = "light_meter")
//    private LightMeter lightMeter;

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "client_id")
//    private Integer clientId;

//    @OneToOne(targetEntity=Client.class, fetch = FetchType.EAGER, cascade=CascadeType.ALL)
//    @JoinColumn(name = "client")
//    private Client client;

}
