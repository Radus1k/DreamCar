package com.example.DreamCar.repositories;

import com.example.DreamCar.models.AppUser;
import com.example.DreamCar.models.Deal;
import com.example.DreamCar.models.Licitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface DealsRepository extends JpaRepository<Deal,Long> {

    @Query("SELECT d FROM  Deals d WHERE d.username=?1")
    List<Deal> findMyDeals(String username);

    @Modifying
    @Query("update Deals d set d.price  = ?1 WHERE d.idDeals=?2 ")
    void updateDealPrice(double Price,long id_deal);

    @Repository
    @Transactional(readOnly = true)
    interface AppUserRepository
            extends JpaRepository<AppUser, Long> {



        Optional<AppUser> findByEmail(String email);
        @Transactional
        @Modifying
        @Query("UPDATE AppUser a " +
                "SET a.enabled = TRUE WHERE a.email = ?1")
        int enableAppUser(String email);




    }
}
