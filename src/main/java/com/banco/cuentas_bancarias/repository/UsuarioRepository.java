package com.banco.cuentas_bancarias.repository;

import com.banco.cuentas_bancarias.model.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //boolean existsByNombreUsuario(String nombreUsuario);
    //Optional<Usuario> findByClienteId(Long clienteId);
    Optional<Usuario> findByUsername(String username);
}
