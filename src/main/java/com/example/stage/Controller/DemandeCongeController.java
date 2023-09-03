package com.example.stage.Controller;

import com.example.stage.Dao.DemandeCongeRepository;
import com.example.stage.Dao.UserRepository;
import com.example.stage.Models.DemandeConge;
import com.example.stage.Models.User;
import com.example.stage.Service.DemandeCongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/gestion")
public class DemandeCongeController {
    private final DemandeCongeService demandeCongeService;
    private final UserRepository userRepository;


    @Autowired
    public DemandeCongeController(DemandeCongeService demandeCongeService, UserRepository userRepository) {
        this.demandeCongeService = demandeCongeService;
        this.userRepository = userRepository;
    }


    @GetMapping("/demande-conge/All")
    public ResponseEntity<List<DemandeConge>> getAllDemandesConge() {
        List<DemandeConge> demandesConge = demandeCongeService.findAll();
        return ResponseEntity.ok(demandesConge);
    }

    // Exemple de méthode pour créer une demande de congé via une requête HTTP POST
    @PostMapping("/demande-conge")
    public ResponseEntity<DemandeConge> createDemandeConge(@RequestBody DemandeConge demandeConge) {
        DemandeConge createdDemandeConge = demandeCongeService.createDemandeConge(demandeConge);

        return ResponseEntity.ok(createdDemandeConge);
    }


    @PutMapping("/valider-demande/{idDemandeConge}")
    public ResponseEntity<DemandeConge> validerDemandeConge(@PathVariable Long idDemandeConge) {
        DemandeConge demandeConge = demandeCongeService.validerDemandeConge(idDemandeConge);

        return ResponseEntity.ok(demandeConge);
    }


    @PutMapping("/annuler-demande/{idDemandeConge}")
    public ResponseEntity<DemandeConge> annulerDemandeConge(@PathVariable Long idDemandeConge) {
        DemandeConge demandeConge = demandeCongeService.RefuserDemandeConge(idDemandeConge);

        return ResponseEntity.ok(demandeConge);
    }


    @GetMapping("/demandes-acceptees/{userId}")
    public ResponseEntity<List<DemandeConge>> getDemandesAccepteesByUser(@PathVariable Long userId) {
        List<DemandeConge> demandesAcceptees = demandeCongeService.findAllDemandeAccepterByIdUser(userId);
        return ResponseEntity.ok(demandesAcceptees);
    }

    @GetMapping("/demandes-en-attente")
    public ResponseEntity<List<DemandeConge>> getAllDemandesEnAttente() {
        List<DemandeConge> demandesEnAttente = demandeCongeService.findAllEnAttente();
        return ResponseEntity.ok(demandesEnAttente);
    }

    @GetMapping("/demandes-Refuser")
    public ResponseEntity<List<DemandeConge>> getAllDemandesRefusee() {
        List<DemandeConge> demandesRefusee = demandeCongeService.findAllRefuser();
        return ResponseEntity.ok(demandesRefusee);
    }

    @GetMapping("/demandes-Valider")
    public ResponseEntity<List<DemandeConge>> getAllDemandesValider() {
        List<DemandeConge> demandesValider = demandeCongeService.findAllValider();
        return ResponseEntity.ok(demandesValider);
    }

    @DeleteMapping("/demande-conge/{id}")
    public ResponseEntity<String> deleteDemandeConge(@PathVariable Long id) {
        demandeCongeService.delete(id);
        return ResponseEntity.ok("Demande de congé supprimée avec succès.");
    }

    @GetMapping("/demande-conge/{id}")
    public ResponseEntity<DemandeConge> getDemandeCongeById(@PathVariable Long id) {
        Optional<DemandeConge> demandeCongeOptional = demandeCongeService.findById(id);

        if (demandeCongeOptional.isPresent()) {
            DemandeConge demandeConge = demandeCongeOptional.get();
            return ResponseEntity.ok(demandeConge);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/demandes-validees/{userId}")
    public ResponseEntity<List<DemandeConge>> getDemandesValideesByUser(@PathVariable Long userId) {
        // Retrieve the User from the database using userId (implement the user retrieval logic)
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            List<DemandeConge> demandesValidees = demandeCongeService.getValiderDemandeCongeByUser(user);
            return ResponseEntity.ok(demandesValidees);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/demandes/{userId}")
    public ResponseEntity<List<DemandeConge>> getDemandesByUser(@PathVariable Long userId) {
        // Retrieve the User from the database using userId (implement the user retrieval logic)
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            List<DemandeConge> demandes = demandeCongeService.findDemandeByUser(user);
            return ResponseEntity.ok(demandes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/demandes-annulees/{userId}")
    public ResponseEntity<List<DemandeConge>> getDemandesAnnuleesByUser(@PathVariable Long userId) {
        // Retrieve the User from the database using userId (implement the user retrieval logic)
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            List<DemandeConge> demandesAnnulees = demandeCongeService.getAnnulerDemandeCongeByUser(user);
            return ResponseEntity.ok(demandesAnnulees);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/demandes-en-cours/{userId}")
    public ResponseEntity<List<DemandeConge>> getDemandesEnCoursByUser(@PathVariable Long userId) {
        // Retrieve the User from the database using userId (implement the user retrieval logic)
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            List<DemandeConge> demandesEnCours = demandeCongeService.getEnCoursDemandeCongeByUser(user);
            return ResponseEntity.ok(demandesEnCours);
        } else {
            return ResponseEntity.notFound().build();
        }

    }



    @GetMapping("/demande-conge/counts")
    public ResponseEntity<Map<String, Long>> getLeaveRequestCountsPerUser() {
        Map<String, Long> leaveRequestCounts = demandeCongeService.getCountOfLeaveRequestsPerUser();
        return ResponseEntity.ok(leaveRequestCounts);
    }
}