package com.sena.app_hotel.Services;

import com.sena.app_hotel.DAO.Entities.ReservasEntity;
import com.sena.app_hotel.DAO.Repositories.HabitacionesRepository;
import com.sena.app_hotel.DAO.Repositories.ReservasRepository;
import com.sena.app_hotel.Exeptions.ObjectResponse;
import com.sena.app_hotel.Interfaces.IUsuariosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UsuariosService implements IUsuariosService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity<ObjectResponse> obtenerTodosLosUsuarios() {
        try{
            String url = "http://localhost:8081/admin/realms/APP_HOTEL/users";

            String adminToken = this.getAdminToken();

            if(adminToken == null){
                return ResponseEntity.ok().body(new ObjectResponse(-1, "No se pudo obtener el token de administración", null));
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(adminToken);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<List> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    List.class
            );
            return ResponseEntity.ok().body(new ObjectResponse(0,"Se obtuvieron los datos correctamente", response.getBody()));
        }catch (Exception e){
            System.out.println("Ocurrió un error al intentar realizar el proceso: "+e.getMessage());
            return ResponseEntity.ok().body(new ObjectResponse(-1, "Ocurrió un error al intentar realizar el proceso: "+e.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<ObjectResponse> crearUsuario(Map<String, Object> newUser) {
        try {
            String url = "http://localhost:8081/admin/realms/APP_HOTEL/users";

            String adminToken = this.getAdminToken();

            if(adminToken == null){
                return ResponseEntity.ok().body(new ObjectResponse(-1, "No se pudo obtener el token de administración", null));
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(adminToken);
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            // Armar el JSON del nuevo usuario
            Map<String, Object> usuario = new HashMap<>();
            usuario.put("username", newUser.get("username"));
            usuario.put("email", newUser.get("email"));
            usuario.put("firstName", newUser.get("firstName"));
            usuario.put("lastName", newUser.get("lastName"));
            usuario.put("enabled", true);
            usuario.put("emailVerified", true);

            Map<String, Object> passwordCreds = new HashMap<>();
            passwordCreds.put("type", "password");
            passwordCreds.put("value", newUser.get("password"));
            passwordCreds.put("temporary", false);

            usuario.put("credentials", Collections.singletonList(passwordCreds));

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(usuario, headers);

            ResponseEntity<Void> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    Void.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok().body(new ObjectResponse(0, "Usuario creado correctamente", null));
            } else {
                return ResponseEntity.ok().body(new ObjectResponse(-1, "No se pudo crear el usuario", null));
            }

        }catch (Exception e){
            System.out.println("Ocurrió un error al intentar realizar el proceso: "+e.getMessage());
            return ResponseEntity.ok().body(new ObjectResponse(-1, "Ocurrió un error al intentar realizar el proceso: "+e.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<ObjectResponse> editarUsuario(Map<String, Object> updatedUser) {
        String adminToken = this.getAdminToken();

        if(adminToken == null){
            return ResponseEntity.ok().body(new ObjectResponse(-1, "No se pudo obtener el token de administración", null));
        }

        String url = "http://localhost:8081/admin/realms/APP_HOTEL/users/" + updatedUser.get("idUser");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(adminToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Armar el JSON del nuevo usuario
        Map<String, Object> usuarioUpdated = new HashMap<>();

        usuarioUpdated.put("username", updatedUser.get("username"));
        usuarioUpdated.put("email", updatedUser.get("email"));
        usuarioUpdated.put("firstName", updatedUser.get("firstName"));
        usuarioUpdated.put("lastName", updatedUser.get("lastName"));
        usuarioUpdated.put("enabled", updatedUser.get("isUpdated"));
        usuarioUpdated.put("emailVerified", updatedUser.get("isEmailVerified"));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(usuarioUpdated, headers);

        try {
            ResponseEntity<Void> response = restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    request,
                    Void.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok().body(new ObjectResponse(0, "Usuario creado correctamente", null));
            } else {
                return ResponseEntity.ok().body(new ObjectResponse(-1, "No se pudo crear el usuario", null));
            }

        } catch (Exception e) {
            System.out.println("Error al actualizar el usuario: " + e.getMessage());
            return ResponseEntity.ok().body(new ObjectResponse(-1, "Ocurrió un error al intentar realizar el proceso: "+e.getMessage(), null));
        }
    }

    @Override
    public ResponseEntity<ObjectResponse> eliminarUsuario(String idUser) {
        String adminToken = this.getAdminToken();

        if(adminToken == null){
            return ResponseEntity.ok().body(new ObjectResponse(-1, "No se pudo obtener el token de administración", null));
        }

        String url = "http://localhost:8081/admin/realms/APP_HOTEL/users/" + idUser;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(adminToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<Void> response = restTemplate.exchange(
                    url,
                    HttpMethod.DELETE,
                    request,
                    Void.class
            );



            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Usuario eliminado correctamente.");
                return ResponseEntity.ok().body(new ObjectResponse(0, "Usuario creado correctamente", null));
            } else {
                return ResponseEntity.ok().body(new ObjectResponse(-1, "No se pudo crear el usuario", null));
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar el usuario: " + e.getMessage());
            return ResponseEntity.ok().body(new ObjectResponse(-1, "Ocurrió un error al intentar realizar el proceso: "+e.getMessage(), null));
        }
    }




    /**
     *      PRIVATE METHODS
     */

    private String getAdminToken(){
        try {
            String url = "http://localhost:8081/realms/APP_HOTEL/protocol/openid-connect/token";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            // Parámetros del body
            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("grant_type", "password");
            body.add("client_id", "admin-cli");
            body.add("username", "admin_app_hotel");
            body.add("password", "admin");

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

            // Hacer el POST y esperar un Map como respuesta
            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

            // Obtener el access_token del JSON de respuesta
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                System.out.println("Se obtuvo correctamente el token de acceso de administración!");
                return (String) response.getBody().get("access_token");
            } else {
                System.out.println("No se pudo obtener el token, código: " + response.getStatusCode());
                return null;
            }

        } catch (Exception e){
            System.out.println("Ocurrió un error al intentar realizar el proceso: "+e.getMessage());
            return null;
        }
    }
}
