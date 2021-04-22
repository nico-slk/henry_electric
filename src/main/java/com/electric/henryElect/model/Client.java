package com.electric.henryElect.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer dni;
    private String name;
    private String lastName;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoices")
    private List<Invoice> invoices;
}
