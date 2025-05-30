package com.sena.app_hotel.Services;
import com.sena.app_hotel.DAO.Repositories.HabitacionesRepository;
import com.sena.app_hotel.DAO.Repositories.ReservasRepository;
import com.sena.app_hotel.Exeptions.ObjectResponse;
import com.sena.app_hotel.Interfaces.IHabitacionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HabitacionService implements IHabitacionesService {

    private final HabitacionesRepository habitacionesRepository;

    public ResponseEntity<ObjectResponse> obtenerTodasLasHabitaciones(){
        try {
            return ResponseEntity.ok().body(new ObjectResponse(0,"El proceso ha terminado con éxito", habitacionesRepository.findAllHabitacionesCustomData()));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(new ObjectResponse(-1,"Ocurrió un error al realizar el proceso " + e.getMessage()));
        }
    };

}
