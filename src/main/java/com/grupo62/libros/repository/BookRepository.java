package com.grupo62.libros.repository;

import java.util.List;

import com.grupo62.libros.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    public List<Book> findByDeletedAtIsNull();
}
