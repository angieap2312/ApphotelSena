package com.sena.app_hotel.DAO.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "habitaciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitacionesEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_habitacion")
        private Integer idHabitacion;

        @Column(name = "tipo_habitacion", nullable = false, length = 50)
        private String tipoHabitacion;

        @Column(name = "descripcion", columnDefinition = "TEXT")
        private String descripcion;

        @Column(name = "precio_noche", nullable = false, precision = 10, scale = 2)
        private BigDecimal precioNoche;

        @Column(name = "capacidad_maxima", nullable = false)
        private Integer capacidadMaxima;

        @Column(name = "cantidad_disponible", nullable = false)
        private Integer cantidadDisponible; // Esto es crucial para la disponibilidad

        @Column(name = "imagen_url", length = 255)
        private String imagenUrl;
    }

