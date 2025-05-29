package com.sena.app_hotel.DAO.Repositories;
import com.sena.app_hotel.DAO.Entities.ReservasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

public interface ReservasRepository extends JpaRepository<ReservasEntity, Long> {

    /**
     * Busca reservas confirmadas para una habitación específica que se superpongan
     * con un rango de fechas dado.
     *
     * @param idHabitacion El ID de la habitación.
     * @param fechaEntrada La fecha de entrada deseada para la nueva reserva.
     * @param fechaSalida La fecha de salida deseada para la nueva reserva.
     * @return Una lista de ReservasEntity que entran en conflicto.
     */
    @Query("SELECT r FROM ReservasEntity r WHERE r.idHabitacion = :idHabitacion " +
            "AND r.estadoReserva = 'CONFIRMADA' " +
            "AND ((r.fechaEntrada < :fechaSalida AND r.fechaSalida > :fechaEntrada))")
    List<ReservasEntity> findConflictingReservations(
            @Param("idHabitacion") Long idHabitacion,
            @Param("fechaEntrada") LocalDate fechaEntrada,
            @Param("fechaSalida") LocalDate fechaSalida);


    List<ReservasEntity> findAllByOrderByIdReservaDesc();
}
