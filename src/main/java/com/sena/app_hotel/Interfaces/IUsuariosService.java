package com.sena.app_hotel.Interfaces;
import com.sena.app_hotel.DAO.Entities.ReservasEntity;
import com.sena.app_hotel.Exeptions.ObjectResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IUsuariosService {

    ResponseEntity<ObjectResponse> obtenerTodosLosUsuarios();

    ResponseEntity<ObjectResponse> crearUsuario(Map<String, Object> newUser);

    ResponseEntity<ObjectResponse> editarUsuario(Map<String, Object> updatedUser);

    ResponseEntity<ObjectResponse> eliminarUsuario(String idUser);
}
