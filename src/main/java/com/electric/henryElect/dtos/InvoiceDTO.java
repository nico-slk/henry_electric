package com.electric.henryElect.dtos;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class InvoiceDTO {

    private LocalDateTime timeInitialMedition; //Fecha y hora medición inicial
    private LocalDateTime timeFinalMedition; //Fecha y hora medición final
    private Double total; //Total a pagar (Consumo * Tarifa)
    private Double kwh;

}
