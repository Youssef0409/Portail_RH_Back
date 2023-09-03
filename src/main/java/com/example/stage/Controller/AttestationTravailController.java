package com.example.stage.Controller;

import com.example.stage.Models.AttestationTravail;
import com.example.stage.Service.AttestationTravailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/gestion")
public class AttestationTravailController {
    private final AttestationTravailService attestationTravailService;

    @Autowired
    public AttestationTravailController(AttestationTravailService attestationTravailService) {
        this.attestationTravailService = attestationTravailService;
    }

    @PostMapping("/demande-attestation-travail")
    public ResponseEntity<AttestationTravail> createDemandeAttestation(@RequestBody AttestationTravail attestationTravail) throws Exception {
        AttestationTravail newAttestationTravail = attestationTravailService.createDemandeAttestation(attestationTravail);
        return ResponseEntity.ok(newAttestationTravail);
    }
    @PostMapping("/valider-attestation-travail/{idAttestationTravail}")
    public ResponseEntity<AttestationTravail> validerAttestationTravail(@PathVariable Long idAttestationTravail) {
        AttestationTravail attestationTravail = attestationTravailService.validerAttestationTravail(idAttestationTravail);
        return ResponseEntity.ok(attestationTravail);
    }

    @PostMapping("/refuser-attestation-travail/{idAttestationTravail}")
    public ResponseEntity<AttestationTravail> refuserAttestationTravail(@PathVariable Long idAttestationTravail) {
        AttestationTravail attestationTravail = attestationTravailService.RefuserAttestationTravail(idAttestationTravail);
        return ResponseEntity.ok(attestationTravail);
    }


    @DeleteMapping("/attestation-travail/{id}")
    public ResponseEntity<Void> deleteAttestationTravail(@PathVariable Long id) {
        attestationTravailService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAttestation/All")
    public ResponseEntity<List<AttestationTravail>> findAllAttestation() {
        List<AttestationTravail> attestations = attestationTravailService.findAllAttestation();
        return ResponseEntity.ok(attestations);
    }

    @GetMapping("/getAttestation/{id}")
    public ResponseEntity<AttestationTravail> findAttestationById(@PathVariable Long id) {
        Optional<AttestationTravail> attestation = attestationTravailService.findAttestationById(id);
        if (attestation.isPresent()) {
            return ResponseEntity.ok(attestation.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/getAttestation/user/{userId}")
    public ResponseEntity<List<AttestationTravail>> findAttestationsByUserId(@PathVariable Long userId) {
        List<AttestationTravail> attestations = attestationTravailService.findAttestationsByUserId(userId);
        return ResponseEntity.ok(attestations);
    }


    @GetMapping("/attestations-en-attente")
    public ResponseEntity<List<AttestationTravail>> getAttestationsEnAttente() {
        List<AttestationTravail> attestationsEnAttente = attestationTravailService.getAttestationsEnAttente();
        return ResponseEntity.ok(attestationsEnAttente);
    }


    @GetMapping("/attestations-accepter")
    public List<AttestationTravail> findAllAttestationAccepter() {
        return attestationTravailService.findAllAttestationAccepter();
    }

    @GetMapping("/attestations-refuser")
    public List<AttestationTravail> findAllAttestationRefuser() {
        return attestationTravailService.findAllAttestationRefuser();
    }

    @GetMapping("/employee/attestations-en-attente/{userId}")
    public ResponseEntity<List<AttestationTravail>> getAttestationsEnAttenteByUserId(@PathVariable Long userId) {
        List<AttestationTravail> attestations = attestationTravailService.findAttestationsEnAttenteByUserId(userId);
        return ResponseEntity.ok(attestations);
    }

    @GetMapping("/employee/attestations-accepter/{userId}")
    public ResponseEntity<List<AttestationTravail>> getAttestationsAccepterByUserId(@PathVariable Long userId) {
        List<AttestationTravail> attestations = attestationTravailService.findAttestationsAccepterByUserId(userId);
        return ResponseEntity.ok(attestations);
    }

    @GetMapping("/employee/attestations-refuser/{userId}")
    public ResponseEntity<List<AttestationTravail>> getAttestationsRefuserByUserId(@PathVariable Long userId) {
        List<AttestationTravail> attestations = attestationTravailService.findAttestationsRefuserByUserId(userId);
        return ResponseEntity.ok(attestations);
    }


    @GetMapping("/attestation/counts")
    public ResponseEntity<Map<String, Long>> getLeaveRequestCountsPerUser() {
        Map<String, Long> leaveRequestCounts = attestationTravailService.getCountOfLeaveRequestsPerUser();
        return ResponseEntity.ok(leaveRequestCounts);
    }
}
