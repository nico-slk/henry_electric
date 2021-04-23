package com.electric.henryElect.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer dni;
    private String name;
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Invoice> invoices;

    @OneToOne(targetEntity=Address.class, fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

}
