package com.example.stage.Service.impl;

import com.example.stage.Dao.AttestationTravailRepository;
import com.example.stage.Dao.UserRepository;
import com.example.stage.Models.AttestationTravail;
import com.example.stage.Models.StatutDemande;
import com.example.stage.Models.User;
import com.example.stage.Service.AttestationTravailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.stage.Models.Role.Employee;

@Service
public class AttestationTravailServiceImpl implements AttestationTravailService {
    private final AttestationTravailRepository attestationTravailRepository;
    private final UserRepository userRepository;
    @Autowired
    public AttestationTravailServiceImpl(AttestationTravailRepository attestationTravailRepository ,UserRepository userRepository) {
        this.attestationTravailRepository = attestationTravailRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AttestationTravail createDemandeAttestation(AttestationTravail attestationTravail) throws Exception {
        User user = attestationTravail.getUser(); // Assuming you can access the user associated with the request

        // Check if the user already has a request with status EN_ATTENTE
        List<AttestationTravail> existingRequests = attestationTravailRepository.findByUserAndStatut(user, StatutDemande.EN_ATTENTE);

        if (!existingRequests.isEmpty()) {
            throw new Exception("You already have a pending request");
        }

        attestationTravail.setStatut(StatutDemande.EN_ATTENTE); // Set the default status to EN_ATTENTE when creating
        return attestationTravailRepository.save(attestationTravail);
    }

    @Override
    public AttestationTravail validerAttestationTravail(Long idAttestationTravail) {
        Optional<AttestationTravail> attestationTravailOptional = attestationTravailRepository.findById(idAttestationTravail);

        if (attestationTravailOptional.isPresent()) {
            AttestationTravail attestationTravail = attestationTravailOptional.get();
            attestationTravail.setStatut(StatutDemande.APPROUVEE); // Set the status to VALIDEE
            return attestationTravailRepository.save(attestationTravail);
        } else {
            throw new NoSuchElementException("AttestationTravail with id " + idAttestationTravail + " not found.");
        }
    }

    @Override
    public AttestationTravail RefuserAttestationTravail(Long idAttestationTravail) {
        Optional<AttestationTravail> attestationTravailOptional = attestationTravailRepository.findById(idAttestationTravail);

        if (attestationTravailOptional.isPresent()) {
            AttestationTravail attestationTravail = attestationTravailOptional.get();
            attestationTravail.setStatut(StatutDemande.REFUSEE); // Set the status to REFUSEE
            return attestationTravailRepository.save(attestationTravail);
        } else {
            throw new NoSuchElementException("AttestationTravail with id " + idAttestationTravail + " not found.");
        }
    }

    @Override
    public void delete(Long id) {
        attestationTravailRepository.deleteById(id);
    }

    public List<AttestationTravail> findAllAttestation() {
        return attestationTravailRepository.findAll();
    }

    public Optional<AttestationTravail> findAttestationById(Long id) {
        return attestationTravailRepository.findById(id);
    }
    public List<AttestationTravail> findAttestationsByUserId(Long userId) {
        return attestationTravailRepository.findByUser_Id(userId);
    }

    public List<AttestationTravail> findAttestationsEnAttenteByUserId(Long userId) {
        return attestationTravailRepository.findByUser_IdAndStatut(userId, StatutDemande.EN_ATTENTE);
    }

    public List<AttestationTravail> findAttestationsAccepterByUserId(Long userId) {
        return attestationTravailRepository.findByUser_IdAndStatut(userId, StatutDemande.APPROUVEE);
    }

    public List<AttestationTravail> findAttestationsRefuserByUserId(Long userId) {
        return attestationTravailRepository.findByUser_IdAndStatut(userId, StatutDemande.REFUSEE);
    }


    public List<AttestationTravail> getAttestationsEnAttente() {
        return attestationTravailRepository.findByStatut(StatutDemande.EN_ATTENTE);
    }

    public List<AttestationTravail> findAllAttestationAccepter() {
        return attestationTravailRepository.findByStatut(StatutDemande.APPROUVEE);
    }

    public List<AttestationTravail> findAllAttestationRefuser() {
        return attestationTravailRepository.findByStatut(StatutDemande.REFUSEE);
    }

    @Override
    public Map<String, Long> getCountOfLeaveRequestsPerUser() {
        List<User> employees = userRepository.findByRole(Employee);
        Map<String, Long> leaveRequestCounts = new HashMap<>();

        for (User employee : employees) {
            Long leaveRequestCount = attestationTravailRepository.countLeaveRequestsByUser(employee.getId());
            leaveRequestCounts.put(employee.getLastname(), leaveRequestCount);
        }


        return leaveRequestCounts;
    }

}
