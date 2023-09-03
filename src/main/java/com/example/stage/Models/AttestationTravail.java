package com.example.stage.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class AttestationTravail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateDemande;
    @Enumerated(EnumType.STRING)
    private StatutDemande statut;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date dateDebutTravail; // Date de début de travail pour l'attestation
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date dateFinTravail; // Date de fin de travail pour l'attestation
    private String motif; // Motif de l'attestation (par exemple: stage, emploi, etc.)
    private String details;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_user")
    private User user;
}
