package com.grupo62.libros.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.grupo62.libros.entity.Editorial;
import com.grupo62.libros.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    public List<Editorial> findAll(){
        return editorialRepository.findByDeletedAtIsNull();
    }

    public Editorial findById(Long id) {
        Optional<Editorial> editorial = editorialRepository.findById(id);
        if (editorial.isPresent()) {
            return editorial.get();
        }
        return null;
    }

    public void create(Editorial editorial){
        editorial.setCreatedAt(new Date());
        editorialRepository.save(editorial);
    }

    public void update(Editorial editorial) {
        editorial.setUpdatedAt(new Date());
        editorialRepository.save(editorial);
    }

    public void delete(Editorial editorial) {
        editorial.setDeletedAt(new Date());
        editorialRepository.save(editorial);
    }   
}
