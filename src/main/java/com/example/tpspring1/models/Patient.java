package com.example.tpspring1.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.management.ConstructorParameters;
import java.util.Collection;
import java.util.Date;
@Entity
@Data
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@ToString
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    @Size(min = 5, max = 40, message = "le nom doit etre entre 5 et 40 caractères")
    private String nom;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;
    private boolean malade;
    @DecimalMin("100")
    private int score;
    @OneToMany(mappedBy = "patient")
    private Collection<RendezVous> rendezVous;

}
