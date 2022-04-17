package com.example.DreamCar.controllers;

import com.example.DreamCar.Services.RegistrationService;
import com.example.DreamCar.appuser.AppUserRole;
import com.example.DreamCar.models.AppUser;
import com.example.DreamCar.models.Deal;
import com.example.DreamCar.registration.RegistrationRequest;
import com.example.DreamCar.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;

@RestController
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final DaoAuthenticationProvider daoAuthenticationProvider;

    @PostMapping(path = "dreamcar/registration/")
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "dreamcar/registration/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

    @RequestMapping(path="/sign_up", method=RequestMethod.GET)
    public ModelAndView sign_up_view(Model model) {
        model.addAttribute("dictKey", "DictValueOfContext");
        model.addAttribute("User", new AppUser());
        return new ModelAndView("/sign-up");
    }

    @Transactional
    @RequestMapping(path="/sign_up", method=RequestMethod.POST)
    public RedirectView updatedealSubmit(@ModelAttribute AppUser user, Principal principal) throws NoSuchAlgorithmException {
        AppUser createdUser = new AppUser();
        createdUser.setFirstName(user.getFirstName());
        createdUser.setLastName(user.getLastName());

        // Save password as hash
        String stringHash = this.bCryptPasswordEncoder.encode(user.getPassword());

        //Saving form data to database
        createdUser.setPassword(stringHash);
        createdUser.setEmail(user.getEmail());
        createdUser.setAppUserRole(AppUserRole.USER);
        createdUser.setEnabled(Boolean.TRUE);
        appUserRepository.save(createdUser);
        System.out.println("User has created his account ");

//        TODO: registrationService.authUser(-> Autentific userul si il salvez in BD.
        return new RedirectView("/", true); // Redirect pe pagina principala
    }


}
