package com.example.stage.Service.impl;

import com.example.stage.Dao.AvanceSalaireRepository;
import com.example.stage.Dao.UserRepository;
import com.example.stage.Models.AttestationTravail;
import com.example.stage.Models.AvanceSalaire;
import com.example.stage.Models.StatutDemande;
import com.example.stage.Models.User;
import com.example.stage.Service.AvanceSalaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.stage.Models.Role.Employee;

@Service
public class AvanceSalaireServiceImpl implements AvanceSalaireService {

    private final AvanceSalaireRepository avanceSalaireRepository;
    private final UserRepository userRepository;
    @Autowired
    public AvanceSalaireServiceImpl(AvanceSalaireRepository avanceSalaireRepository,UserRepository userRepository) {
        this.avanceSalaireRepository = avanceSalaireRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AvanceSalaire createDemandeAvanceSalaire(AvanceSalaire avanceSalaire) throws Exception {
        User user = avanceSalaire.getUser();

        List<AvanceSalaire> existingRequests = avanceSalaireRepository.findByUserAndStatut(user, StatutDemande.EN_ATTENTE);

        if (!existingRequests.isEmpty()) {
            throw new Exception("You already have a pending request");
        }

        avanceSalaire.setStatut(StatutDemande.EN_ATTENTE);
        return avanceSalaireRepository.save(avanceSalaire);
    }








    @Override
    public AvanceSalaire validerAvanceSalaire(Long idAvanceSalaire) {
        Optional<AvanceSalaire> avanceSalaireOptional = avanceSalaireRepository.findById(idAvanceSalaire);

        if (avanceSalaireOptional.isPresent()) {
            AvanceSalaire avanceSalaire = avanceSalaireOptional.get();
            avanceSalaire.setStatut(StatutDemande.APPROUVEE); // Set the status to VALIDEE
            return avanceSalaireRepository.save(avanceSalaire);
        } else {
            throw new NoSuchElementException("AvanceSalaire with id " + idAvanceSalaire + " not found.");
        }
    }

    @Override
    public AvanceSalaire refuserAvanceSalaire(Long idAvanceSalaire) {
        Optional<AvanceSalaire> avanceSalaireOptional = avanceSalaireRepository.findById(idAvanceSalaire);

        if (avanceSalaireOptional.isPresent()) {
            AvanceSalaire avanceSalaire = avanceSalaireOptional.get();
            avanceSalaire.setStatut(StatutDemande.REFUSEE); // Set the status to REFUSEE
            return avanceSalaireRepository.save(avanceSalaire);
        } else {
            throw new NoSuchElementException("AvanceSalaire with id " + idAvanceSalaire + " not found.");
        }
    }

    @Override
    public void deleteAvanceSalaire(Long idAvanceSalaire) {
        avanceSalaireRepository.deleteById(idAvanceSalaire);
    }



    public List<AvanceSalaire> findAvanceSalaireByIdUser(Long userId) {
        return avanceSalaireRepository.findByUser_Id(userId);
    }

    public List<AvanceSalaire> findAllAvanceSalaire() {
        return avanceSalaireRepository.findAll();
    }

    public AvanceSalaire findAvanceById(Long avanceId) {
        return avanceSalaireRepository.findById(avanceId).orElse(null);
    }

    public List<AvanceSalaire> getAllAvanceSalairesEnAttente() {
        return avanceSalaireRepository.findByStatut(StatutDemande.EN_ATTENTE);
    }

    public List<AvanceSalaire> getAllAvanceSalairesAccepter() {
        return avanceSalaireRepository.findByStatut(StatutDemande.APPROUVEE);
    }

    public List<AvanceSalaire> getAllAvanceSalairesRefuser() {
        return avanceSalaireRepository.findByStatut(StatutDemande.REFUSEE);
    }


    public List<AvanceSalaire> findAvanceSalaireEnAttenteByUserId(Long userId) {
        return avanceSalaireRepository.findByUser_IdAndStatut(userId, StatutDemande.EN_ATTENTE);
    }

    public List<AvanceSalaire> findAvanceSalaireAccepterByUserId(Long userId) {
        return avanceSalaireRepository.findByUser_IdAndStatut(userId, StatutDemande.APPROUVEE);
    }

    public List<AvanceSalaire> findAvanceSalaireRefuserByUserId(Long userId) {
        return avanceSalaireRepository.findByUser_IdAndStatut(userId, StatutDemande.REFUSEE);
    }


    @Override
    public Map<String, Long> getCountOfLeaveRequestsPerEmployee() {
        List<User> employees = userRepository.findByRole(Employee);
        Map<String, Long> leaveRequestCounts = new HashMap<>();

        for (User employee : employees) {
            Long leaveRequestCount = avanceSalaireRepository.countLeaveRequestsByUser(employee.getId());
            leaveRequestCounts.put(employee.getLastname(), leaveRequestCount);
        }

        return leaveRequestCounts;
    }

}
