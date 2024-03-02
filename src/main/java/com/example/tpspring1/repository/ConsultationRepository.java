package com.example.tpspring1.repository;

import com.example.tpspring1.models.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
