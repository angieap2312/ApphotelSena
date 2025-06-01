package com.sena.app_hotel.DAO.Repositories;
import com.sena.app_hotel.DAO.Entities.HabitacionesEntity;
import com.sena.app_hotel.Payload.Responses.HabitacionesCustomDataDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface HabitacionesRepository extends JpaRepository <HabitacionesEntity, Long> {

    @Query(value = """
            SELECT
              id_habitacion,
              Nombre_habitacion,
              tipo_habitacion,
              estado_habitacion,
              precio_noche,
              imagen_url,
              cantidad_disponible
            FROM habitaciones
            """, nativeQuery = true)
    List<HabitacionesCustomDataDTO> findAllHabitacionesCustomData();
}
