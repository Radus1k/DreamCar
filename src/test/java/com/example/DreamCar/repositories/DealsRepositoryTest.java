package com.example.DreamCar.repositories;


import com.example.DreamCar.models.Deal;
import com.example.DreamCar.models.Licitation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DealsRepositoryTest {

    @Autowired
    private DealsRepository dealsRepository;

    @Autowired
    private LicitationRepository licitationRepository;

    @Test
    public void itShouldSaveObjects(){
        Deal deal = new Deal(50L,600,20L);
        dealsRepository.save(deal);

        boolean expected_exists = dealsRepository.existsById(50L);
        assertTrue(expected_exists);
    }

    @Test
    public void itShouldGetUsernamesObjects(){
        Licitation licitation = licitationRepository.getById(1L);

        Deal deal_1 = new Deal(500.0,  licitation.getid_licitation(),"test_user1" );
        Deal deal_2 = new Deal(600.0,  licitation.getid_licitation(),"test_user1");
        Deal deal_3 = new Deal(500.0,  licitation.getid_licitation(),"test_user2");
        dealsRepository.saveAll(
                List.of(deal_1, deal_2, deal_3));

        List<Deal> list_of_deals_for_user1 = dealsRepository.findMyDeals("test_user1");
        List<Deal> list_of_deals_for_user2 = dealsRepository.findMyDeals("test_user2");

        assertEquals(list_of_deals_for_user1.size(),2);
        assertEquals(list_of_deals_for_user2.size(),1);
    }

    @Test
    public void itShouldUpdateDealPrice(){
        long id_Deal = 1L;
        Optional<Deal> deal = dealsRepository.findById(id_Deal);
        deal.ifPresent(value -> assertEquals(value.getPrice(), 100));
        dealsRepository.updateDealPrice(10050, id_Deal);
        Deal updated_deal = dealsRepository.getById(id_Deal);
        assertEquals(updated_deal.getPrice(), 10050);
    }





}
