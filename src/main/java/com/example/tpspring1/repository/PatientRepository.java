package com.example.tpspring1.repository;

import com.example.tpspring1.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByNom(String nome);
}
