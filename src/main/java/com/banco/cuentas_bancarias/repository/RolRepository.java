package com.banco.cuentas_bancarias.repository;

import com.banco.cuentas_bancarias.model.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    boolean existsByNombre(String nombre);
    Optional<Rol> findByNombre(String nombre);
}
