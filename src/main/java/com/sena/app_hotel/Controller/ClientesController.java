package com.sena.app_hotel.Controller;

import com.sena.app_hotel.Exeptions.ObjectResponse;
import com.sena.app_hotel.Interfaces.IClientesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/clientes")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ClientesController {

    private final IClientesService iClientesService;

    // Endpoint para obtener todas los clientes
    @GetMapping("get-all-clients")
    public ResponseEntity<ObjectResponse> obtenerTodosLosClientes() {
        return iClientesService.getCustomClientes();
    }
}
