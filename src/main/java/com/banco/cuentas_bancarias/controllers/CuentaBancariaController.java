package com.banco.cuentas_bancarias.controllers;

import com.banco.cuentas_bancarias.model.domain.CuentaBancaria;
import com.banco.cuentas_bancarias.services.CuentaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cuentas-bancarias")
public class CuentaBancariaController {

    @Autowired
    private CuentaBancariaService cuentaBancariaService;

    @GetMapping
    public List<CuentaBancaria> obtenerTodasLasCuentasBancarias(){
        return cuentaBancariaService.obtenerTodasLasCuentasBancarias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaBancaria> obtenerCuentaBancariaPorId(@PathVariable Long id){
        Optional<CuentaBancaria> cuentaBancaria = cuentaBancariaService.obtenerCuentaBancariaPorId(id);
        return cuentaBancaria.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CuentaBancaria guardarCuentaBancaria(@RequestBody CuentaBancaria cuentaBancaria){
        return cuentaBancariaService.guardarCuentaBancaria(cuentaBancaria);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuentaBancaria(@PathVariable Long id){
        cuentaBancariaService.eliminarCuentaBancaria(id);
        return ResponseEntity.noContent().build();
    }
}
