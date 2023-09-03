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
public class DemandeConge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date dateDebut;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date dateFin;
    @Enumerated(EnumType.STRING)
    private TypeConge type;
    @Enumerated(EnumType.STRING)
    private StatutDemande statut;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_user")
    private User user;
}
