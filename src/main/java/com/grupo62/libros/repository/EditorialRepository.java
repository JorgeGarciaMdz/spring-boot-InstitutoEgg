package com.grupo62.libros.repository;

import java.util.List;

import com.grupo62.libros.entity.Editorial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, Long>{
    
    public List<Editorial> findByDeletedAtIsNull();
}
