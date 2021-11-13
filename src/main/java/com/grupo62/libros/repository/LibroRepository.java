package com.grupo62.libros.repository;

import java.util.List;

import com.grupo62.libros.entity.Libro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    
    public List<Libro> findByDeletedAtIsNull();
}
