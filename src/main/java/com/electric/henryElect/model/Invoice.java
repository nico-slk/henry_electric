package com.electric.henryElect.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime timeInitialMedition; //Fecha y hora medición inicial
    private LocalDateTime timeFinalMedition; //Fecha y hora medición final
    private Double rate; //Tipo de tarifa
    private Double total; //Total a pagar (Consumo * Tarifa)
    private Boolean pay;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

}
