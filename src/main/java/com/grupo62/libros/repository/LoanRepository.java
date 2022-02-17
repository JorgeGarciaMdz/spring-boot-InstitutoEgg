package com.grupo62.libros.repository;

import java.util.List;

import com.grupo62.libros.entity.Loan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long>{
    
    public List<Loan> findByDeletedAtIsNull();
}
