package com.sena.app_hotel.DAO.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long idReserva;

    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "id_habitacion")
    private Long idHabitacion;

    @Column(name = "fecha_entrada", nullable = false)
    private LocalDate fechaEntrada;

    @Column(name = "fecha_salida", nullable = false)
    private LocalDate fechaSalida;

    @Column(name = "cantidad_habitaciones")
    private Integer cantidadHabitaciones;

    @Column(name = "numero_huespedes", nullable = false)
    private Integer numeroHuespedes;

    @Column(name = "precio_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioTotal;

    @Column(name = "estado_reserva", nullable = false, length = 50)
    private String estadoReserva;

    @Column(name = "fecha_reserva", nullable = false, insertable = false)
    private LocalDate fechaReserva;

    @Column(name = "notas", columnDefinition = "TEXT")
    private String notas;

    /*@PrePersist
    protected void onCreate() {
        if (fechaReserva == null) {
            fechaReserva = LocalDateTime.now();
        }
    }*/
}
