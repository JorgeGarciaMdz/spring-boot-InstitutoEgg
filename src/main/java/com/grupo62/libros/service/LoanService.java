package com.grupo62.libros.service;

import java.util.List;
import java.util.Optional;

import com.grupo62.libros.entity.Loan;
import com.grupo62.libros.repository.LoanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public List<Loan> findAll() {
        return loanRepository.findByDeletedAtIsNull();
    }

    public Loan findById(Long id) {
        Optional<Loan> loan = loanRepository.findById(id);
        if (loan.isPresent())
            return loan.get();
        return null;
    }

    public void save(Loan loan){
        loanRepository.save(loan);
    }
}
