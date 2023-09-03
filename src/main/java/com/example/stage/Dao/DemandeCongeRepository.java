package com.example.stage.Dao;

import com.example.stage.Models.DemandeConge;
import com.example.stage.Models.StatutDemande;
import com.example.stage.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource
@EnableJpaRepositories
public interface DemandeCongeRepository extends JpaRepository<DemandeConge,Long> {
    boolean existsByUserAndStatut(User user, StatutDemande statut);
  //  List<DemandeConge> findAllDemandeAccepterByIdUser(Long id);
  @Query("SELECT dc FROM DemandeConge dc WHERE dc.user.id = :userId AND dc.statut = 'APPROUVEE'")
  List<DemandeConge> findAllDemandeAccepterByIdUser(Long userId);

    List<DemandeConge> findByUserAndStatut(User user, StatutDemande statut);
    List<DemandeConge> findByUser(User user);

    List<DemandeConge> findByStatut(StatutDemande statut);
  List<DemandeConge> findByUser_IdAndStatut(Long userId, StatutDemande statut);

  @Query("SELECT COUNT(dc) FROM DemandeConge dc WHERE dc.user.id = :userId")
  Long countLeaveRequestsByUser(@Param("userId") Long userId);


}
