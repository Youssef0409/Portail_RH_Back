package com.example.stage.Dao;

import com.example.stage.Models.AttestationTravail;
import com.example.stage.Models.AvanceSalaire;
import com.example.stage.Models.StatutDemande;
import com.example.stage.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AvanceSalaireRepository extends JpaRepository<AvanceSalaire,Long> {
    List<AvanceSalaire> findByUserAndStatut(User user, StatutDemande statut);
    List<AvanceSalaire> findByUser_Id(Long userId);
    List<AvanceSalaire> findByStatut(StatutDemande statut);
    List<AvanceSalaire> findByUser_IdAndStatut(Long userId, StatutDemande statut);

    @Query("SELECT COUNT(dc) FROM AvanceSalaire dc WHERE dc.user.id = :userId")
    Long countLeaveRequestsByUser(@Param("userId") Long userId);

}
