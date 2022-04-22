package com.example.DreamCar.repositories;

import com.example.DreamCar.models.Licitation;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class LicitationRepositoryTest {

    @Autowired
    private LicitationRepository licitationRepository;

    @BeforeEach
    void setUp() {
        Licitation inactive_lic = new Licitation(3L, "stop_spate", 3, LocalDateTime.now().plusMinutes(10L), 5000, false);
        licitationRepository.save(inactive_lic);
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    public void itShouldSaveObjects() {
        Licitation temp_lic  = new Licitation(15L, "stop_spate", 3, LocalDateTime.now().plusMinutes(10L), 200, true);
        licitationRepository.save(temp_lic);

        boolean expected_exists = licitationRepository.existsById(15L);
        assertTrue(expected_exists);
    }

    @Test
    public void itShouldReturnAllActiveObjects() {
        List<Licitation> all_licitations = licitationRepository.findAllActiveLicitations();
        assertEquals(all_licitations.size(), 2); // 2 ACTIVE LICITATIONS
        assertEquals(all_licitations.get(0).getTargetPrice(), 100);
        assertEquals(all_licitations.get(1).getTargetPrice(), 740);
    }

    @Test
    public void itShouldUpdateObjects() {
        long id_lic = 3L;
        Licitation lic = licitationRepository.getById(id_lic);
        assertEquals(lic.getTargetPrice(), 5000);
        licitationRepository.updateLicitationTargetPrice(850, id_lic);
        Licitation updated_lic = licitationRepository.getById(id_lic);
        assertEquals(updated_lic.getTargetPrice(), 850);
    }


    @Test
    public void itShouldFindObjectsById(){
        Optional<Licitation> lic = licitationRepository.findById(1L);
        lic.ifPresent(licitation -> assertEquals(licitation.getTargetPrice(), 100));

    }



}