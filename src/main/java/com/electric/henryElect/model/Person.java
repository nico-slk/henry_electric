package com.electric.henryElect.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Person implements Serializable {

    @Id
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client")
    private Client client;

}
