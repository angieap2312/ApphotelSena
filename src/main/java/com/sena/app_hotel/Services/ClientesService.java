package com.sena.app_hotel.Services;

import com.sena.app_hotel.DAO.Repositories.ClientesRepository;
import com.sena.app_hotel.Exeptions.ObjectResponse;
import com.sena.app_hotel.Interfaces.IClientesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientesService implements IClientesService {

    private final ClientesRepository clientesRepository;

    @Override
    public ResponseEntity<ObjectResponse> getCustomClientes() {
        try {
            return ResponseEntity.ok().body(new ObjectResponse(0,"El proceso ha terminado con éxito", clientesRepository.getCustomDataClientes()));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(new ObjectResponse(-1,"Ocurrió un error al realizar el proceso " + e.getMessage()));
        }
    }
}
