package com.example.DreamCar;


import com.example.DreamCar.models.Deal;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ActiveProfiles("postgresql")
//public class EntityManagerTest {
//
//    @Autowired
//    private EntityManager entityManager;
//
//    @Test
//
//    public void findDealById(){
//        Deal dealFound = entityManager.find(Deal.class, 1L);
//
//        assertEquals(dealFound.getPrice(), 100);
//
//    }
//
//
//}
