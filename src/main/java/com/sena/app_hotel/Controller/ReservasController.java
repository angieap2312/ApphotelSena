package com.sena.app_hotel.Controller;
import com.sena.app_hotel.DAO.Entities.ReservasEntity;
import com.sena.app_hotel.Exeptions.ObjectResponse;
import com.sena.app_hotel.Interfaces.IReservasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/reservas")
@CrossOrigin("*")
public class ReservasController {

    @Autowired
    private IReservasService iReservasService;

    // Endpoint para obtener todas las reservas
    @GetMapping("get-all-reservations")
    public ResponseEntity<ObjectResponse> obtenerTodasLasReservas() {
       return iReservasService.obtenerTodasLasReservas();
    }

    // Endpoint para obtener una reserva por ID
    @GetMapping("/reservas/{id}") // Aquí {id} es la variable de ruta
    public ResponseEntity<ObjectResponse> obtenerReservaPorId(@PathVariable Long id) {
        return iReservasService.obtenerReservaPorId(id);
    }

    // Endpoint para crear una reserva
    @PostMapping("/create-reservation")
    public ResponseEntity<ObjectResponse> crearReserva(@RequestBody ReservasEntity reserva) {
        // Validación de fechas lógicas
        if (reserva.getFechaEntrada().isAfter(reserva.getFechaSalida())) {
            return ResponseEntity.badRequest().body(new ObjectResponse(-1, "La fecha de entrada no puede ser posterior a la fecha de salida."));
        }
        if (reserva.getFechaEntrada().isEqual(reserva.getFechaSalida())) {
            return ResponseEntity.badRequest().body(new ObjectResponse(-1, "La fecha de entrada no puede ser igual a la fecha de salida."));
        }

        return iReservasService.crearReserva(reserva, false);
    }

    // Endpoint para editar una reserva
    @PostMapping("/edit-reservation")
    public ResponseEntity<ObjectResponse> editarReserva(@RequestBody ReservasEntity reserva) {

        return iReservasService.crearReserva(reserva, true);
    }

    // Endpoint para eliminar una reserva
    @PostMapping("/delete-reservation")
    public ResponseEntity<ObjectResponse> eliminarReserva(@RequestBody Long idReserva) {

        return iReservasService.eliminarReserva(idReserva);
    }
}
