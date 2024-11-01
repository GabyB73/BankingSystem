package com.banco.cuentas_bancarias.services;

import com.banco.cuentas_bancarias.model.domain.Rol;
import com.banco.cuentas_bancarias.model.domain.Usuario;
import com.banco.cuentas_bancarias.repository.RolRepository;
import com.banco.cuentas_bancarias.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario crearUsuarioConRol(String nombre, String apellido, String username, String password, String rolNombre){

       //Crear usuario con el nombre, apellido, username y password codificado
        Usuario usuario = new Usuario(nombre, apellido, username, passwordEncoder.encode(password));

        //Buscar el rol por su nombre en la bbdd
        Optional<Rol> rol = rolRepository.findByNombre(rolNombre);

        //Manejar el caso en que el rol no se encuentre
        Rol foundRol = rol.orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));

        //Añadir el rol encontrado al conjunto de roles del usuario
        usuario.getRoles().add(foundRol);

        //Guardar y retornar el usuario
        return usuarioRepository.save(usuario);
    }
}
