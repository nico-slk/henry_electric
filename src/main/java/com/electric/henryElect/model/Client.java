package com.electric.henryElect.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "lightMeter_id")
    private List<Integer> lightMeterId;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "Invoices_id")
    private List<Integer> invoiceId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Integer addressid;

//    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
//    @JoinColumn(name = "address_id")
//    private List<Integer> addressid;

}
