package com.grupo62.libros.service;

import com.grupo62.libros.repository.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grupo62.libros.entity.Author;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findAll(){
        return authorRepository.findByDeletedAtIsNull();
    }

    public Author findById( Long id){
        Optional<Author> author = authorRepository.findById(id);
        if( author.isPresent() ){
            return author.get();
        }
        return null;
    }

    public Author create(Author author){
        author.setCreatedAt(new Date());
        author.setUpdatedAt(new Date());
        authorRepository.save(author);
        return author;
    }

    public Author update(Author author){
        author.setUpdatedAt((new Date()));
        authorRepository.save(author);
        return author;
    }

    public void delete( Author author){
        author.setDeletedAt(new Date());
        authorRepository.save(author);
    }
}
