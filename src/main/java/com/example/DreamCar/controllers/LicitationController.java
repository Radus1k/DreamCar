package com.example.DreamCar.controllers;

import com.example.DreamCar.Services.LicitationService;
import com.example.DreamCar.models.Deal;
import com.example.DreamCar.models.Licitation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path="dreamcar/licitation/")
public class LicitationController {

    private final LicitationService licitationService;

    @Autowired
    public LicitationController(LicitationService licitationService) {
        this.licitationService = licitationService;
    }

    @GetMapping
    public List<Licitation> getActiveLicitations(){
        return licitationService.getActiveLicitations();
    }

    @GetMapping(path="all/")
    public List<Licitation> getAllLicitations(){
        System.out.println("Getting all licitations...");
        return licitationService.getAllLicitations();
    }

    public void setWinner(Long licitationId, String winner) throws IllegalAccessException {
        licitationService.setWinner(licitationId, winner);
    }


    @PostMapping
    public void registerNewLicitation(@RequestBody Licitation licitation, Principal principal) throws IllegalAccessException {

        licitationService.addNewLicitation(licitation,  principal);
    }

    @PostMapping(path="{licitationId}/deal")
    public void addDealForLicitation(@RequestBody Deal deal, @PathVariable("licitationId") Long licitationId) throws IllegalAccessException {
        licitationService.addDealForLicitation(licitationId, deal);
    }

    @DeleteMapping(path="{licitationId}")
    public void deleteLicitation(@PathVariable("licitationId") Long licitationId) throws IllegalAccessException {
        licitationService.deleteLicitation(licitationId);

        }
}
