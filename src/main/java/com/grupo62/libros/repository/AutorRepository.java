package com.grupo62.libros.repository;

import java.util.List;

import com.grupo62.libros.entity.Autor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{
    
    public List<Autor> findByDeletedAtIsNull();
}


