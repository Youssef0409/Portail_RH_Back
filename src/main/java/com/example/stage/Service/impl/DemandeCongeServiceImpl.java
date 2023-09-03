package com.example.stage.Service.impl;

import com.example.stage.Dao.DemandeCongeRepository;
import com.example.stage.Dao.UserRepository;
import com.example.stage.Models.DemandeConge;
import com.example.stage.Models.StatutDemande;
import com.example.stage.Models.User;
import com.example.stage.Service.DemandeCongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.stage.Models.Role.Employee;

@Service
public class DemandeCongeServiceImpl implements DemandeCongeService {

    private final DemandeCongeRepository demandeCongeRepository;

    private final UserRepository userRepository;

    @Autowired
    public DemandeCongeServiceImpl(DemandeCongeRepository demandeCongeRepository,UserRepository userRepository) {
        this.demandeCongeRepository = demandeCongeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<DemandeConge> findAll() {
        return demandeCongeRepository.findAll();
    }

    @Override
    public DemandeConge createDemandeConge(DemandeConge demandeConge) {
        User user = demandeConge.getUser();
        if (hasDemandeEnAttente(user)) {
            throw new IllegalArgumentException("Vous avez déjà une demande en attente de validation.");
        }

        demandeConge.setStatut(StatutDemande.EN_ATTENTE);
        return demandeCongeRepository.save(demandeConge);
    }

    @Override
    public boolean hasDemandeEnAttente(User user) {
        return demandeCongeRepository.existsByUserAndStatut(user, StatutDemande.EN_ATTENTE);
    }


    @Override
    public DemandeConge validerDemandeConge(Long idDemandeConge) {
        // Récupérer la demande de congé depuis la base de données en utilisant l'ID
        Optional<DemandeConge> demandeCongeOptional = demandeCongeRepository.findById(idDemandeConge);

        if (demandeCongeOptional.isPresent()) {
            DemandeConge demandeConge = demandeCongeOptional.get();
            // Mettre à jour le statut de la demande pour le valider (vous pouvez ajouter d'autres logiques ici)
            demandeConge.setStatut(StatutDemande.APPROUVEE);
            return demandeCongeRepository.save(demandeConge);
        } else {
            // Gérer le cas où la demande de congé avec l'ID donné n'a pas été trouvée
            throw new IllegalArgumentException("La demande de congé avec l'ID spécifié n'a pas été trouvée.");
        }
    }

    @Override
    public DemandeConge RefuserDemandeConge(Long idDemandeConge) {
        // Récupérer la demande de congé depuis la base de données en utilisant l'ID
        Optional<DemandeConge> demandeCongeOptional = demandeCongeRepository.findById(idDemandeConge);

        if (demandeCongeOptional.isPresent()) {
            DemandeConge demandeConge = demandeCongeOptional.get();
            // Mettre à jour le statut de la demande pour l'annuler (vous pouvez ajouter d'autres logiques ici)
            demandeConge.setStatut(StatutDemande.REFUSEE);
            return demandeCongeRepository.save(demandeConge);
        } else {
            // Gérer le cas où la demande de congé avec l'ID donné n'a pas été trouvée
            throw new IllegalArgumentException("La demande de congé avec l'ID spécifié n'a pas été trouvée.");
        }
    }


    @Override
    public List<DemandeConge> findAllDemandeAccepterByIdUser(Long idUser) {
        return demandeCongeRepository.findAllDemandeAccepterByIdUser(idUser);
    }

    public List<DemandeConge> findAllEnAttente() {
        return demandeCongeRepository.findByStatut(StatutDemande.EN_ATTENTE);
    }

    public List<DemandeConge> findAllRefuser() {
        return demandeCongeRepository.findByStatut(StatutDemande.REFUSEE);
    }

    public List<DemandeConge> findAllValider() {
        return demandeCongeRepository.findByStatut(StatutDemande.APPROUVEE);
    }

    @Override
    public void delete(Long id) {
        demandeCongeRepository.deleteById(id);
    }

    @Override
    public Optional<DemandeConge> findById(Long id) {
        return demandeCongeRepository.findById(id);
    }


    @Override
    public List<DemandeConge> getValiderDemandeCongeByUser(User user) {
        return demandeCongeRepository.findByUserAndStatut(user, StatutDemande.APPROUVEE);
    }

    @Override
    public List<DemandeConge> findDemandeByUser(User user) {
        return demandeCongeRepository.findByUser(user);
    }

    @Override
    public List<DemandeConge> getAnnulerDemandeCongeByUser(User user) {
        return demandeCongeRepository.findByUserAndStatut(user, StatutDemande.REFUSEE);
    }

    @Override
    public List<DemandeConge> getEnCoursDemandeCongeByUser(User user) {
        return demandeCongeRepository.findByUserAndStatut(user, StatutDemande.EN_ATTENTE);
    }



    @Override
    public Map<String, Long> getCountOfLeaveRequestsPerUser() {
        List<User> employees = userRepository.findByRole(Employee);
        Map<String, Long> leaveRequestCounts = new HashMap<>();

        for (User employee : employees) {
            Long leaveRequestCount = demandeCongeRepository.countLeaveRequestsByUser(employee.getId());
            leaveRequestCounts.put(employee.getLastname(), leaveRequestCount);
        }

        return leaveRequestCounts;
    }

}
