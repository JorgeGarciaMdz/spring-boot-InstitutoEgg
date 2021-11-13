package com.grupo62.libros.service;

import com.grupo62.libros.repository.AutorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grupo62.libros.entity.Autor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AutorService {
    
    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> findAll(){
        // return autorRepository.findAll();
        return autorRepository.findByDeletedAtIsNull();
    }

    public Autor findById( Long id){
        Optional<Autor> autor = autorRepository.findById(id);
        if( autor.isPresent() ){
            return autor.get();
        }
        return null;
    }

    public Autor saveAutor(Autor autor){
        autor.setUpdatedAt(new Date());
        autorRepository.save(autor);
        return autor;
    }

    public void deleteAutor( Autor autor){
        autor.setDeletedAt(new Date());
        autorRepository.save(autor);
    }
}
