package com.banco.cuentas_bancarias.repository;

import com.banco.cuentas_bancarias.model.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    //Definir los métodos necesarios para obtener los clientes
}
