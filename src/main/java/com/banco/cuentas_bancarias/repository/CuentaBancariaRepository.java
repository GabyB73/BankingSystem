package com.banco.cuentas_bancarias.repository;

import com.banco.cuentas_bancarias.model.domain.CuentaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaBancariaRepository extends JpaRepository<CuentaBancaria, Long> {
    //Definir los métodos necesarios para obtener las cuentas bancarias
}
