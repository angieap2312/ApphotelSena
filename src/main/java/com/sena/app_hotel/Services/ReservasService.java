package com.sena.app_hotel.Services;
import com.sena.app_hotel.DAO.Entities.HabitacionesEntity;
import com.sena.app_hotel.DAO.Entities.ReservasEntity;
import com.sena.app_hotel.DAO.Repositories.HabitacionesRepository;
import com.sena.app_hotel.DAO.Repositories.ReservasRepository;
import com.sena.app_hotel.Exeptions.ObjectResponse;
import com.sena.app_hotel.Interfaces.IReservasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservasService implements IReservasService {

    private final ReservasRepository reservasRepository;
    private final HabitacionesRepository habitacionesRepository;

    // Operación: Obtener todas las reservas
    @Override
    public ResponseEntity<ObjectResponse> obtenerTodasLasReservas() {
        try {
            return ResponseEntity.ok().body(new ObjectResponse(0,"El proceso ha terminado con éxito", reservasRepository.findAllReservasCustomData()));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(new ObjectResponse(-1,"Ocurrió un error al realizar el proceso " + e.getMessage()));
        }
    }

    // Operación: Obtener una reserva por ID
    @Override
    public ResponseEntity<ObjectResponse> obtenerReservaPorId(Long id) {
        try {
            return ResponseEntity.ok().body(new ObjectResponse(0,"El proceso ha terminado con éxito", reservasRepository.findById(id)));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(new ObjectResponse(-1,"Ocurrió un error al realizar el proceso " + e.getMessage()));
        }
    }

    //Método para crear una reserva
    @Override
    public ResponseEntity<ObjectResponse> crearReserva(ReservasEntity reserva, boolean isUpdating) {
        try {
            if (isUpdating){
                Optional <ReservasEntity> entidadReservas = reservasRepository.findById(reserva.getIdReserva());
                if (entidadReservas.isEmpty()){
                    return ResponseEntity.badRequest().body(new ObjectResponse(-1, "La reserva que se desea actualizar no existe!"));
                }else {
                    reserva.setPrecioTotal(entidadReservas.get().getPrecioTotal());
                    reserva.setNotas(entidadReservas.get().getNotas());
                }
            }
            // 1. Validar que la habitación exista y esté disponible
            Optional<HabitacionesEntity> habitacionOptional = habitacionesRepository.findById(reserva.getIdHabitacion().longValue());
            if (habitacionOptional.isEmpty()) {
                return ResponseEntity.badRequest().body(new ObjectResponse(-1, "La habitación especificada no existe."));
            }

            HabitacionesEntity habitacion = habitacionOptional.get();

            // Verificar si hay disponibilidad en la cantidad (si la habitación es de un tipo con varias unidades)
            if (habitacion.getCantidadDisponible() <= 0) {
                return ResponseEntity.badRequest().body(new ObjectResponse(-1, "La habitación no tiene disponibilidad."));
            }

            // 2. Validar superposición de fechas para la habitación
            List<ReservasEntity> reservasConflictivas = reservasRepository.findConflictingReservations(
                    reserva.getIdHabitacion(),
                    reserva.getFechaEntrada(),
                    reserva.getFechaSalida()
            );

            if (!reservasConflictivas.isEmpty()) {
                // Puedes refinar este mensaje para mostrar las fechas exactas si lo deseas
                return ResponseEntity.badRequest().body(new ObjectResponse(-1, "La habitación ya está reservada para las fechas seleccionadas."));
            }

            // 3. Establecer el estado de la reserva a CONFIRMADA
            reserva.setEstadoReserva("CONFIRMADA");

            // 4. Establecer la fecha de reserva al día actual (si no se hace con @PrePersist)
            // Si la fechaReserva se debe establecer automáticamente al crear, y tu entidad tiene @PrePersist
            // protegido void onCreate() { if (fechaReserva == null) { fechaReserva = LocalDate.now(); } },
            // entonces no necesitas esta línea. Si no tienes @PrePersist para fechaReserva, agrégala:
            if (reserva.getFechaReserva() == null) {
                reserva.setFechaReserva(LocalDate.now());
            }

            // 5. Opcional: Decrementar la cantidad disponible de la habitación
            // Esto es importante si manejas un inventario de habitaciones.
            // Si una habitación es única (ej. "Habitación 101"), y no manejas cantidadDisponible para el tipo,
            // entonces el decremento no aplica directamente a "cantidad_disponible" sino a un estado de la habitación
            // o a una lógica más compleja de inventario.
            // Pero si "cantidad_disponible" es para un tipo de habitación del cual hay varias, sí aplica.
            // Para este ejemplo, asumimos que "cantidad_disponible" se refiere a unidades de un tipo de habitación.
            //habitacion.setCantidadDisponible(habitacion.getCantidadDisponible() - reserva.getCantidadHabitaciones());
            habitacionesRepository.save(habitacion); // Guardar la actualización de la habitación


            // 6. Guardar la reserva
            reservasRepository.save(reserva);
            return ResponseEntity.ok().body(new ObjectResponse(0,"El proceso ha terminado con éxito y la reserva ha sido confirmada."));

        } catch (Exception e) {
            // Considera loggear la excepción para depuración
            return ResponseEntity.internalServerError().body(new ObjectResponse(-1,"Ocurrió un error al realizar el proceso: " + e.getMessage()));
        }
    }

    //Método para eliminar una reserva por id
    @Override
    public ResponseEntity<ObjectResponse> eliminarReserva(Long idReserva){
        System.out.println(idReserva);
        try {
            Optional <ReservasEntity> entidadReservas = reservasRepository.findById(idReserva);
            if (entidadReservas.isEmpty()){
                return ResponseEntity.badRequest().body(new ObjectResponse(-1, "La reserva que se desea eliminar no existe!"));
            }else {
                reservasRepository.deleteById(entidadReservas.get().getIdReserva());
                return ResponseEntity.ok().body(new ObjectResponse(0,"El proceso se ha realizado exitosamente!"));
            }
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(new ObjectResponse(-1,"Ocurrió un error al realizar el proceso: " + e.getMessage()));
        }
    };


}
