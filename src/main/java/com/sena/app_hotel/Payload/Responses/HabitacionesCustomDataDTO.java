package com.sena.app_hotel.Payload.Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HabitacionesCustomDataDTO {

    private Object idHabitacion;
    private Object nombreHabitacion;
    private Object tipoHabitacion;
    private Object estadoHabitacion;
    private Object precioNoche;
    private Object imagenUrl;
    private Object cantidadDisponible;

}
