package com.grupo62.libros.repository;

import java.util.List;

import com.grupo62.libros.entity.Author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
    
    public List<Author> findByDeletedAtIsNull();
}
