package com.example.tpspring1.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor

public class RendezVous {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private Date dateRdv;
    @Enumerated(EnumType.STRING)
    private StatusRdv statusRdv;
    @ManyToOne
    Patient patient;
    @ManyToOne
    Medecin medecin;
    @OneToOne(mappedBy = "rendezVous")
    private Consultation consultation;
}
