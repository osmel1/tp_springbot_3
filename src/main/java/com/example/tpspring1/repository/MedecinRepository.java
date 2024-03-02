package com.example.tpspring1.repository;

import com.example.tpspring1.models.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository  extends JpaRepository<Medecin, Long>{

    public Medecin findByNom(String nome);
}
