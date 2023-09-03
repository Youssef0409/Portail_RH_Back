package com.example.stage.Dao;

import com.example.stage.Models.AttestationTravail;
import com.example.stage.Models.StatutDemande;
import com.example.stage.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AttestationTravailRepository extends JpaRepository<AttestationTravail,Long> {
    List<AttestationTravail> findByUserAndStatut(User user, StatutDemande statut);
    List<AttestationTravail> findByUser_Id(Long userId);
    List<AttestationTravail> findByStatut(StatutDemande statut);
    List<AttestationTravail> findByUser_IdAndStatut(Long userId, StatutDemande statut);
    @Query("SELECT COUNT(dc) FROM AttestationTravail dc WHERE dc.user.id = :userId")
    Long countLeaveRequestsByUser(@Param("userId") Long userId);
}
