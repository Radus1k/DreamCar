package com.example.DreamCar.config;

import com.example.DreamCar.appuser.AppUserRole;
import com.example.DreamCar.models.AppUser;
import com.example.DreamCar.repositories.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppUserConfig {

    @Bean
    CommandLineRunner commandLineAppUserRunner(AppUserRepository repository){

        return args -> {
            AppUser admin = new AppUser("marius", "radu", "admin@test.net", "password", AppUserRole.ADMIN,false, true);
            repository.save(admin);
        };
    }
}