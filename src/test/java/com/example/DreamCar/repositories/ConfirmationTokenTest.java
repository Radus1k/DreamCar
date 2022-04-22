package com.example.DreamCar.repositories;


import com.example.DreamCar.appuser.AppUserRole;
import com.example.DreamCar.models.AppUser;
import com.example.DreamCar.models.ConfirmationToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConfirmationTokenTest {

    @Autowired ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired AppUserRepository appUserRepository;

    @Test
    public void itShouldSaveTokens(){
        AppUser user = new AppUser("radu", "marius", "adminConfirmationToken@test.net", "$2a$10$806BSZrjXPoLrsZkznA8nex51CrNuWXrMgiuJsHLE.5.hx4PpdW8C", AppUserRole.ADMIN,false, true);
        appUserRepository.save(user);
        ConfirmationToken token = new ConfirmationToken("NewTokenTest", LocalDateTime.now().minusMinutes(20L),LocalDateTime.now().plusMinutes(20L),user);
        confirmationTokenRepository.save(token);

        Optional<ConfirmationToken> expected_token = confirmationTokenRepository.findByToken("NewTokenTest");

        if(expected_token.isPresent()){
            assertEquals("NewTokenTest",expected_token.get().getToken());
            assertEquals("radu",expected_token.get().getAppUser().getFirstName());
            assertEquals("marius",expected_token.get().getAppUser().getLastName());
        }
        else{
            fail(); // Apprently it didnt save it. Oups
        }

    }

    @Test
    public void itShouldUpdateTokenConfirmed(){
        Optional<AppUser> user = appUserRepository.findByEmail("admin@test.net");
        if(user.isPresent()) {
            LocalDateTime confirmed_yesterday = LocalDateTime.now().minusHours(24);
            ConfirmationToken token = new ConfirmationToken("NewTokenTest", LocalDateTime.now().minusMinutes(20L), LocalDateTime.now().plusMinutes(20L), user.get());
            token.setConfirmedAt(confirmed_yesterday);
            confirmationTokenRepository.save(token);
            confirmationTokenRepository.updateConfirmedAt("NewTokenTest", LocalDateTime.now().plusMinutes(20));

            Optional<ConfirmationToken> updated_token = confirmationTokenRepository.findByToken("NewTokenTest");
            if (updated_token.isPresent()) {
                assertTrue(updated_token.get().getConfirmedAt().isAfter(LocalDateTime.now())); // Confirmed at should be in 20 minutes;
                assertTrue(confirmed_yesterday.isBefore(LocalDateTime.now()));
            } else {
                fail();//Apparently it didnt save it.
            }
        }
        else{
            fail(); // No admin in config actually
        }

    }


}
