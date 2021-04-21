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
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer dni;
    private String name;
    private String lastName;


    @ElementCollection(fetch = FetchType.EAGER)
    @JoinColumn(name = "lightMeter_id")
    private List<Integer> lightMeterId;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Invoice.class)
    @JoinColumn(name = "Invoices_id")
    private List<Integer> invoiceId;

    @JoinColumn(name = "address_id")
    private Integer addressid;

//    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
//    @JoinColumn(name = "address_id")
//    private List<Integer> addressid;

}
