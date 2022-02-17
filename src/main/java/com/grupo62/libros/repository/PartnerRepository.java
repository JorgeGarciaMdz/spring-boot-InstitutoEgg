package com.grupo62.libros.repository;

import java.util.List;

import com.grupo62.libros.entity.Partner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository <Partner, Long> {
    
    public List<Partner> findByDeletedAtIsNull();
}
