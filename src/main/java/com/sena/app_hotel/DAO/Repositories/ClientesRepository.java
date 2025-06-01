package com.sena.app_hotel.DAO.Repositories;
import com.sena.app_hotel.DAO.Entities.ClientesEntity;
import com.sena.app_hotel.Payload.Responses.ClientesCustomDataDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientesRepository extends JpaRepository<ClientesEntity, Long> {

    @Query(value = """
            SELECT
                id_cliente as id,
                CONCAT(CONCAT(id_cliente, ' - '), nombre, apellido) AS label
            FROM
                clientes
            """, nativeQuery = true)
    List<ClientesCustomDataDTO> getCustomDataClientes();
}
