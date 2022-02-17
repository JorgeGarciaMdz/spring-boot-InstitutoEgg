package com.grupo62.libros.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.grupo62.libros.entity.Ejemplar;
import com.grupo62.libros.repository.EjemplarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EjemplarService {

    @Autowired
    private EjemplarRepository ejemplarRepository;

    public List<Ejemplar> findAll() {
        return ejemplarRepository.findByDeletedAtIsNull();
    }

    public void save(Ejemplar ejemplar) {
        ejemplarRepository.save(ejemplar);
    }

    public Ejemplar findById(Long id) {
        Optional<Ejemplar> ejemplar = ejemplarRepository.findById(id);
        if (ejemplar.isPresent())
            return ejemplar.get();
        return null;
    }

    public void delete(Ejemplar ejemplar) {
        ejemplar.setDeletedAt(new Date());
        ejemplarRepository.save(ejemplar);
    }
}
