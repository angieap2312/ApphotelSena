package com.sena.app_hotel.Interfaces;

import com.sena.app_hotel.Exeptions.ObjectResponse;
import org.springframework.http.ResponseEntity;

public interface IClientesService {
    ResponseEntity<ObjectResponse> getCustomClientes();
}
