package com.sena.app_hotel.Interfaces;
import com.sena.app_hotel.DAO.Entities.ReservasEntity;
import com.sena.app_hotel.Exeptions.ObjectResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IReservasService {

    ResponseEntity<ObjectResponse> obtenerTodasLasReservas();

    ResponseEntity<ObjectResponse> obtenerReservaPorId(Long id);

    ResponseEntity <ObjectResponse> crearReserva(ReservasEntity reserva, boolean isUpdating);

    ResponseEntity<ObjectResponse> eliminarReserva(Long id);

}
