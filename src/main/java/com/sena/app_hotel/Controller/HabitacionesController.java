package com.sena.app_hotel.Controller;

import com.sena.app_hotel.Exeptions.ObjectResponse;
import com.sena.app_hotel.Interfaces.IHabitacionesService;
import com.sena.app_hotel.Interfaces.IReservasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/habitaciones")
@CrossOrigin("*")
public class HabitacionesController {

    @Autowired
    private IHabitacionesService iHabitacionesService;

    // Endpoint para obtener todas las habitaciones
    @GetMapping("get-all-rooms")
    public ResponseEntity<ObjectResponse> obtenerTodasLasHabitaciones() {
        return iHabitacionesService.obtenerTodasLasHabitaciones();
    }

}
