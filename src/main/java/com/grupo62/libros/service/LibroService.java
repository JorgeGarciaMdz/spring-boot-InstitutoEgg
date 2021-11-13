package com.grupo62.libros.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.grupo62.libros.entity.Libro;
import com.grupo62.libros.repository.LibroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService {
    

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> findAll(){
        return libroRepository.findByDeletedAtIsNull();
    }

    public void saveLibro(Libro libro) {
        libroRepository.save(libro);
    }

    public Libro findById(Long id) {
        Optional<Libro> libro = libroRepository.findById(id);
        if( libro.isPresent())
            return libro.get();
        return null;
    }

    public void delete(Libro libro) {
        libro.setDeletedAt(new Date());
        libroRepository.save(libro);
    }
}
