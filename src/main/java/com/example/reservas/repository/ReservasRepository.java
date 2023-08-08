package com.example.reservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.reservas.domain.Reservas;

@Repository
public interface ReservasRepository extends JpaRepository<Reservas, Integer> {
    
}
