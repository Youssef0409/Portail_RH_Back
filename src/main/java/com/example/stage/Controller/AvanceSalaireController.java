package com.example.stage.Controller;

import com.example.stage.Models.AttestationTravail;
import com.example.stage.Models.AvanceSalaire;
import com.example.stage.Service.AvanceSalaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gestion")
public class AvanceSalaireController {

    private final AvanceSalaireService avanceSalaireService;

    @Autowired
    public AvanceSalaireController(AvanceSalaireService avanceSalaireService) {
        this.avanceSalaireService = avanceSalaireService;
    }

    @GetMapping("/getAvance/en-attente")
    public List<AvanceSalaire> getAllAvanceSalairesEnAttente() {
        return avanceSalaireService.getAllAvanceSalairesEnAttente();
    }

    @GetMapping("/getAvance/Accepter")
    public List<AvanceSalaire> getAllAvanceSalairesAccepter() {
        return avanceSalaireService.getAllAvanceSalairesAccepter();
    }


    @GetMapping("/getAvance/Refuser")
    public List<AvanceSalaire> getAllAvanceSalairesRefuse() {
        return avanceSalaireService.getAllAvanceSalairesRefuser();
    }

    @PostMapping("/demande-avance-salaire")
    public ResponseEntity<AvanceSalaire> createDemandeAvanceSalaire(@RequestBody AvanceSalaire avanceSalaire) throws Exception {
        AvanceSalaire newAvanceSalaire = avanceSalaireService.createDemandeAvanceSalaire(avanceSalaire);
        return ResponseEntity.ok(newAvanceSalaire);
    }

    @PostMapping("/valider-avance-salaire/{idAvanceSalaire}")
    public ResponseEntity<AvanceSalaire> validerAvanceSalaire(@PathVariable Long idAvanceSalaire) {
        AvanceSalaire avanceSalaire = avanceSalaireService.validerAvanceSalaire(idAvanceSalaire);
        return ResponseEntity.ok(avanceSalaire);
    }

    @PostMapping("/refuser-avance-salaire/{idAvanceSalaire}")
    public ResponseEntity<AvanceSalaire> refuserAvanceSalaire(@PathVariable Long idAvanceSalaire) {
        AvanceSalaire avanceSalaire = avanceSalaireService.refuserAvanceSalaire(idAvanceSalaire);
        return ResponseEntity.ok(avanceSalaire);
    }

    @DeleteMapping("/avance-salaire/{idAvanceSalaire}")
    public ResponseEntity<Void> deleteAvanceSalaire(@PathVariable Long idAvanceSalaire) {
        avanceSalaireService.deleteAvanceSalaire(idAvanceSalaire);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/findAvance/user/{userId}")
    public ResponseEntity<List<AvanceSalaire>> findAvanceSalaireByIdUser(@PathVariable Long userId) {
        List<AvanceSalaire> avances = avanceSalaireService.findAvanceSalaireByIdUser(userId);
        return ResponseEntity.ok(avances);
    }

    @GetMapping("/findAvance/all")
    public ResponseEntity<List<AvanceSalaire>> findAllAvanceSalaire() {
        List<AvanceSalaire> avances = avanceSalaireService.findAllAvanceSalaire();
        return ResponseEntity.ok(avances);
    }

    @GetMapping("/findAvance/{avanceId}")
    public ResponseEntity<AvanceSalaire> findAvanceById(@PathVariable Long avanceId) {
        AvanceSalaire avance = avanceSalaireService.findAvanceById(avanceId);
        if (avance != null) {
            return ResponseEntity.ok(avance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/employee/salaire-en-attente/{userId}")
    public ResponseEntity<List<AvanceSalaire>> getAvanceSalaireEnAttenteByUserId(@PathVariable Long userId) {
        List<AvanceSalaire> attestations = avanceSalaireService.findAvanceSalaireEnAttenteByUserId(userId);
        return ResponseEntity.ok(attestations);
    }

    @GetMapping("/employee/salaire-accepter/{userId}")
    public ResponseEntity<List<AvanceSalaire>> getAvanceSalaireAccepterByUserId(@PathVariable Long userId) {
        List<AvanceSalaire> attestations = avanceSalaireService.findAvanceSalaireAccepterByUserId(userId);
        return ResponseEntity.ok(attestations);
    }

    @GetMapping("/employee/salaire-refuser/{userId}")
    public ResponseEntity<List<AvanceSalaire>> getAvanceSalaireRefuserByUserId(@PathVariable Long userId) {
        List<AvanceSalaire> attestations =avanceSalaireService.findAvanceSalaireRefuserByUserId(userId);
        return ResponseEntity.ok(attestations);
    }

    @GetMapping("/avance-salaire/counts")
    public ResponseEntity<Map<String, Long>> getLeaveRequestCountsPerUser() {
        Map<String, Long> leaveRequestCounts = avanceSalaireService.getCountOfLeaveRequestsPerEmployee();
        return ResponseEntity.ok(leaveRequestCounts);
    }
}
