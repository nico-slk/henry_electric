package com.electric.henryElect.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Invoice{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime timeInitialMedition; //Fecha y hora medición inicial
    private LocalDate timeFinalMedition; //Fecha y hora medición final
    private Double rate; //Tipo de tarifa
    private Double total; //Total a pagar (Consumo * Tarifa)
    private Boolean pay;

    @ManyToOne(targetEntity=Client.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "client")
    private Client client;

}
