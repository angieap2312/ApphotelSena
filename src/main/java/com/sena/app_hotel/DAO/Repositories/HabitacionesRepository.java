package com.sena.app_hotel.DAO.Repositories;
import com.sena.app_hotel.DAO.Entities.HabitacionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacionesRepository extends JpaRepository <HabitacionesEntity, Long> {
}
