package com.sena.app_hotel.Payload.Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservasCustomDataDTO {

    private Object idReserva;
    private Object idHabitacion;
    private Object tipoHabitacion;
    private Object idCliente;
    private Object cliente;
    private Object fechaEntrada;
    private Object fechaSalida;
    private Object numeroHuespedes;
    private Object estadoReserva;
    private Object fechaReserva;
}
