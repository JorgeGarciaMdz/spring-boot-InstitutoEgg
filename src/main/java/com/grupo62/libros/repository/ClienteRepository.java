package com.grupo62.libros.repository;

import java.util.List;

import com.grupo62.libros.entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long> {
    
    public List<Cliente> findByDeletedAtIsNull();
}
