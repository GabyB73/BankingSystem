package com.banco.cuentas_bancarias.services;

import com.banco.cuentas_bancarias.model.domain.CuentaBancaria;
import com.banco.cuentas_bancarias.repository.CuentaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaBancariaService {

    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository;

    public List<CuentaBancaria> obtenerTodasLasCuentasBancarias() {
        return cuentaBancariaRepository.findAll();
    }

    public Optional<CuentaBancaria> obtenerCuentaBancariaPorId(Long id) {
        return cuentaBancariaRepository.findById(id);
    }

    public CuentaBancaria guardarCuentaBancaria(CuentaBancaria cuentaBancaria) {
        return cuentaBancariaRepository.save(cuentaBancaria);
    }

    public void eliminarCuentaBancaria(Long id) {
        cuentaBancariaRepository.deleteById(id);
    }
}
