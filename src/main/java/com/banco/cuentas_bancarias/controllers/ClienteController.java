package com.banco.cuentas_bancarias.controllers;

import com.banco.cuentas_bancarias.model.domain.Cliente;
import com.banco.cuentas_bancarias.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Método para crear un nuevo cliente
    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteService.crearCliente(cliente);
    }

    // Método para guardar un nuevo cliente
    /*@PostMapping
    public Cliente guardarCliente(@RequestBody Cliente cliente) {
        return clienteService.guardarCliente(cliente);
    }*/

    // Método para obtener todos los clientes
    @GetMapping
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteService.obtenerClientes();
    }

    // Método para obtener un cliente por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.obtenerClientePorId(id);
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Método para actualización completa con PUT
        @PutMapping("/{id}")
        public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteActualizado) {
            Optional<Cliente> clienteExistente = clienteService.obtenerClientePorId(id);

            if (clienteExistente.isPresent()) {
                // Reemplazamos todos los campos del cliente existente con los nuevos valores
                Cliente cliente = clienteExistente.get();
                cliente.setNombre(clienteActualizado.getNombre());
                cliente.setApellido(clienteActualizado.getApellido());
                // Actualizar otros campos aquí si es necesario

                clienteService.guardarCliente(cliente);
                return ResponseEntity.ok(cliente);
            } else {
                // Si el cliente no existe, respondemos con 404 Not Found
                return ResponseEntity.notFound().build();
            }
        }

        // Método para actualización parcial con PATCH
        @PatchMapping("/{id}")
        public ResponseEntity<Cliente> actualizarParcialCliente(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
            Optional<Cliente> clienteExistente = clienteService.obtenerClientePorId(id);

            if (clienteExistente.isPresent()) {
                Cliente cliente = clienteExistente.get();

                // Aplicamos las actualizaciones parciales
                updates.forEach((campo, valor) -> {
                    switch (campo) {
                        case "nombre": cliente.setNombre((String) valor); break;
                        case "apellido": cliente.setApellido((String) valor); break;
                        // Otros campos a actualizar si es necesario
                    }
                });

                clienteService.guardarCliente(cliente);
                return ResponseEntity.ok(cliente);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

    // Método para eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
