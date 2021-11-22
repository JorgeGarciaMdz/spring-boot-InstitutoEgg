package com.grupo62.libros.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.grupo62.libros.entity.Cliente;

import com.grupo62.libros.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findByDeletedAtIsNull();
    }

    public void saveCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public Cliente findById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent())
            return cliente.get();
        return null;
    }

    public void deleteCliente(Cliente cliente) {
        cliente.setDeletedAt(new Date());
        clienteRepository.save(cliente);
    }
}
