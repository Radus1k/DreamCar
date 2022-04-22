package com.example.DreamCar.repositories;

import com.example.DreamCar.models.Licitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;


@Repository
public interface LicitationRepository extends JpaRepository<Licitation,Long> {


    @Query("SELECT l FROM Licitation l WHERE l.status = true")
    List<Licitation> findAllActiveLicitations();

    @Query("SELECT l FROM Licitation l WHERE l.id_licitation =?1")
    Licitation getById(Long id_licitation);


    @Modifying
    @Transactional
    @Query("UPDATE Licitation l set l.targetPrice  = ?1 WHERE l.id_licitation=?2 ")
    void updateLicitationTargetPrice(double Price,long id_licitation);


}
