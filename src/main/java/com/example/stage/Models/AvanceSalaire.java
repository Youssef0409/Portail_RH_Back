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
public class AvanceSalaire {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date dateDemande;
    @Enumerated(EnumType.STRING)
    private StatutDemande statut;
    private double montantDemande; // Montant de l'avance demandée
    private Date dateRemboursement; // Date prévue pour le remboursement
    private String justificatif; // Chemin vers le justificatif de la demande
    // Autres attributs pertinents

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_user")
    private User user;
}
