package com.example.DreamCar.config;

import com.example.DreamCar.models.Deal;
import com.example.DreamCar.models.Licitation;
import com.example.DreamCar.repositories.DealsRepository;
import com.example.DreamCar.repositories.LicitationRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DreamCarConfigs {

    @Configuration
    public static class LicitationConfig {

        @Bean
        CommandLineRunner commandLineLicitationRunner(LicitationRepository licitationRepository, DealsRepository dealsRepository) {
            LocalDateTime now = LocalDateTime.now();
            return args -> {

                List<Deal> deals_for_first_lic = new ArrayList<>();
                deals_for_first_lic.add(new Deal(100, 1L, "user1"));
                deals_for_first_lic.add(new Deal(200, 1L, "user2"));

                List<Deal> deals_for_second_lic = new ArrayList<>();
                deals_for_second_lic.add(new Deal(400, 2L, "user1"));
                deals_for_second_lic.add(new Deal(500, 2L, "user2"));

                Licitation lic1 = new Licitation(1L, "bara_fata", 2, now.plusMinutes(20L), 100, true, deals_for_first_lic);
                Licitation lic2 = new Licitation(2L, "stop_spate", 3, now.plusMinutes(10L), 740, true, deals_for_second_lic);
                Licitation inactive_lic = new Licitation(3L, "stop_spate", 3, now.plusMinutes(10L), 5000, false);
                licitationRepository.saveAll(
                        List.of(lic1, lic2, inactive_lic)
                );
                dealsRepository.saveAll(deals_for_first_lic);
                dealsRepository.saveAll(deals_for_second_lic);
            };
        }
    }
}