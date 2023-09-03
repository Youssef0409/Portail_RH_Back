package com.example.stage.Service;

import com.example.stage.Models.AttestationTravail;
import com.example.stage.Models.DemandeConge;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AttestationTravailService {

    AttestationTravail createDemandeAttestation(AttestationTravail attestationTravail) throws Exception;
    List<AttestationTravail> getAttestationsEnAttente();
    AttestationTravail  validerAttestationTravail(Long idAttestationTravail );
    AttestationTravail  RefuserAttestationTravail (Long idAttestationTravail );
    void delete(Long id);
    List<AttestationTravail> findAllAttestation();
    Optional<AttestationTravail> findAttestationById(Long id);
    List<AttestationTravail> findAttestationsByUserId(Long userId);
    List<AttestationTravail> findAllAttestationRefuser();
    List<AttestationTravail> findAllAttestationAccepter();
    List<AttestationTravail> findAttestationsRefuserByUserId(Long userId);
    List<AttestationTravail> findAttestationsAccepterByUserId(Long userId);
    List<AttestationTravail> findAttestationsEnAttenteByUserId(Long userId);
    Map<String, Long> getCountOfLeaveRequestsPerUser();





}
