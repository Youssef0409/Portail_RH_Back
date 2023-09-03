package com.example.stage.Service;

import com.example.stage.Models.AttestationTravail;
import com.example.stage.Models.AvanceSalaire;

import java.util.List;
import java.util.Map;

public interface AvanceSalaireService {
    AvanceSalaire createDemandeAvanceSalaire(AvanceSalaire avanceSalaire) throws Exception;
    AvanceSalaire validerAvanceSalaire(Long idAvanceSalaire);
    AvanceSalaire refuserAvanceSalaire(Long idAvanceSalaire);
    void deleteAvanceSalaire(Long idAvanceSalaire);
    List<AvanceSalaire> findAvanceSalaireByIdUser(Long userId);
    List<AvanceSalaire> findAllAvanceSalaire();
    AvanceSalaire findAvanceById(Long avanceId);
    List<AvanceSalaire> getAllAvanceSalairesEnAttente();
    List<AvanceSalaire> getAllAvanceSalairesAccepter();
    List<AvanceSalaire> getAllAvanceSalairesRefuser();

    List<AvanceSalaire>findAvanceSalaireRefuserByUserId(Long userId);
    List<AvanceSalaire> findAvanceSalaireAccepterByUserId(Long userId);
    List<AvanceSalaire> findAvanceSalaireEnAttenteByUserId(Long userId);
    Map<String, Long>  getCountOfLeaveRequestsPerEmployee();
}
