package com.example.DreamCar.repositories;

import com.example.DreamCar.appuser.AppUserRole;
import com.example.DreamCar.models.AppUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    public void itShouldSaveUsers(){
        AppUser user = new AppUser("Radu", "Marius", "email@test.net","1234", AppUserRole.USER);
        appUserRepository.save(user);

        assertTrue(appUserRepository.existsById(user.getId()));
    }

    @Test
    public void itShouldGetByEmail(){
        AppUser adminTest = new AppUser("radu", "mariusTest", "adminTest@test.net", "$2a$10$806BSZrjXPoLrsZkznA8nex51CrNuWXrMgiuJsHLE.5.hx4PpdW8C", AppUserRole.ADMIN,false, true);
        appUserRepository.save(adminTest);
        String lastName="", firstName="", email="";

        Optional<AppUser> admin_user = appUserRepository.findByEmail("adminTest@test.net");
        if(admin_user.isPresent()){
            lastName=admin_user.get().getLastName();
            firstName=admin_user.get().getFirstName();
            email=admin_user.get().getEmail();
        }

        assertEquals("mariusTest",lastName);
        assertEquals("radu",firstName);
        assertEquals("adminTest@test.net",email);
    }

}
