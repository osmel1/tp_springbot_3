package com.example.tpspring1.service;

import com.example.tpspring1.models.Consultation;
import com.example.tpspring1.models.Medecin;
import com.example.tpspring1.models.Patient;
import com.example.tpspring1.models.RendezVous;

public interface HospitalService {
     Patient enregistrerPatient(Patient patient);
     Medecin enregistrerMedecin(Medecin medecin);
     RendezVous enregistrerRendezVous(RendezVous rendezVous);
     Consultation enregistrerConsultation(Consultation consultation);
}
