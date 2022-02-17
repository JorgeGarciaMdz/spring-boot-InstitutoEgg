package com.grupo62.libros.repository;

import java.util.List;

import com.grupo62.libros.entity.Ejemplar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjemplarRepository extends JpaRepository<Ejemplar, Long> {

    public List<Ejemplar> findByDeletedAtIsNull();
    
}
