package com.example.DreamCar.repositories;

import com.example.DreamCar.models.Licitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface LicitationRepository extends JpaRepository<Licitation,Long> {


    @Query("SELECT l FROM Licitation l WHERE l.status = true")
    List<Licitation> findAllActiveLicitations();



}
