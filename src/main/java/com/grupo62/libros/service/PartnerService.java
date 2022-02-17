package com.grupo62.libros.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.grupo62.libros.entity.Partner;
import com.grupo62.libros.repository.PartnerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerService {

    @Autowired
    private PartnerRepository partnerRepository;

    public List<Partner> findAll() {
        return partnerRepository.findByDeletedAtIsNull();
    }

    public void save(Partner partner) {
        partnerRepository.save(partner);
    }

    public Partner findById(Long id) {
        Optional<Partner> partner = partnerRepository.findById(id);
        if (partner.isPresent())
            return partner.get();
        return null;
    }

    public void delete(Partner partner) {
        partner.setDeletedAt(new Date());
        partnerRepository.save(partner);
    }
}
