package com.grupo62.libros.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.grupo62.libros.entity.Book;
import com.grupo62.libros.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll(){
        return bookRepository.findByDeletedAtIsNull();
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public Book findById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if( book.isPresent())
            return book.get();
        return null;
    }

    public void delete(Book book) {
        book.setDeletedAt(new Date());
        bookRepository.save(book);
    }
}
