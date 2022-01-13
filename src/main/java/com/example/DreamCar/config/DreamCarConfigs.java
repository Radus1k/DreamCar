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
import java.util.List;

@Configuration
public class DreamCarConfigs {

    @Bean
    CommandLineRunner commandLineDealsRunner(DealsRepository repository) {

        return args -> {
            Deal d1 = new Deal(300);
            Deal d2 = new Deal(500);


            repository.saveAll(
                    List.of(d1, d2)
            );
        };
    }

    @Configuration
    public static class LicitationConfig {

        @Bean
        CommandLineRunner commandLineLicitationRunner(LicitationRepository repository){
            LocalDateTime now = LocalDateTime.now();
            return args -> {
                Licitation lic1 = new Licitation(1L,"sport",2, now.plusMinutes(20L),100,true);
                Licitation lic2 = new Licitation(2L,"sport",3, now.plusMinutes(10L),100,true);


            repository.saveAll(
                    List.of(lic1, lic2)
            );
            };
        }
    }
}