package com.example.stage.Service;

import com.example.stage.Models.DemandeConge;
import com.example.stage.Models.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DemandeCongeService {
    boolean hasDemandeEnAttente(User user);
    DemandeConge createDemandeConge(DemandeConge demandeConge);
    DemandeConge validerDemandeConge(Long idDemandeConge);
    DemandeConge RefuserDemandeConge(Long idDemandeConge);
    Optional<DemandeConge> findById(Long id);
    List<DemandeConge> findAll();
  List<DemandeConge> findAllDemandeAccepterByIdUser(Long idUser);
    public List<DemandeConge> findAllEnAttente();
    public List<DemandeConge> findAllRefuser();
    public List<DemandeConge> findAllValider();
    public List<DemandeConge> getValiderDemandeCongeByUser(User user);
    List<DemandeConge> findDemandeByUser(User user);
    public List<DemandeConge> getAnnulerDemandeCongeByUser(User user);
    public List<DemandeConge> getEnCoursDemandeCongeByUser(User user);
    void delete(Long id);
   // long getNombreDemandesValides();
   // long getNombreDemandesEnCours();
   // long getNombreDemandesAnnulees();
   // public List<DemandeFormation> findAllAnnulerEn_Cours();
   Map<String, Long> getCountOfLeaveRequestsPerUser();
}
