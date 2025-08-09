package com.sena.app_hotel.Controller;
import com.sena.app_hotel.DAO.Entities.ReservasEntity;
import com.sena.app_hotel.Exeptions.ObjectResponse;
import com.sena.app_hotel.Interfaces.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("api/usuarios")
@CrossOrigin("*")
public class UsuariosController {

    @Autowired
    private IUsuariosService iUsuariosService;

    // Endpoint para obtener todas las reservas
    @GetMapping("get-all-users")
    public ResponseEntity<ObjectResponse> obtenerTodosLosUsuarios() {
       return iUsuariosService.obtenerTodosLosUsuarios();
    }

    // Endpoint para crear un usuario
    @PostMapping("/create-user")
    public ResponseEntity<ObjectResponse> crearUsuario(@RequestBody Map<String, Object> newUser) {
        return iUsuariosService.crearUsuario(newUser);
    }

    // Endpoint para editar una reserva
    @PostMapping("/edit-user")
    public ResponseEntity<ObjectResponse> editarUsuario(@RequestBody Map<String, Object> updatedUser) {

        return iUsuariosService.editarUsuario(updatedUser);
    }

    // Endpoint para eliminar una reserva
    @PostMapping("/delete-user")
    public ResponseEntity<ObjectResponse> eliminarUsuario(String idUser) {
        System.out.println("user received: " + idUser);
        return iUsuariosService.eliminarUsuario(idUser);
    }
}
