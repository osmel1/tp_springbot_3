package com.example.tpspring1.service;

import com.example.tpspring1.models.Patient;

import java.util.List;

public interface PatService {
    public Patient ajouterPatient(Patient patient);
    public List<Patient> ConsulterTousPatients();
    public Patient ConsulterPatient(Long id);
    public List<Patient> rechercherPatients(String nom);
    public void supprimerPatient(Long id);
    public void modifierPatient(long id ,Patient patient);
}
