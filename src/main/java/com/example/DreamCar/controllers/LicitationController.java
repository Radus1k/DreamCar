package com.example.DreamCar.controllers;

import com.example.DreamCar.Services.LicitationService;
import com.example.DreamCar.models.Deal;
import com.example.DreamCar.models.Licitation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController

public class LicitationController {

    private final LicitationService licitationService;

    @Autowired
    public LicitationController(LicitationService licitationService) {
        this.licitationService = licitationService;
    }

//    @GetMapping
//    public List<Licitation> getActiveLicitations(){
//        return licitationService.getActiveLicitations();
//    }

    @RequestMapping(path="/all_licitations")
    public ModelAndView getAllLicitations(Model model){
        System.out.println("Getting all licitations...");
        model.addAttribute("listaLicitatii", licitationService.getAllLicitations());
        return new ModelAndView("/licitations");
        //return "test";
        //return licitationService.getAllLicitations();
    }

    @RequestMapping(path="/add_licitation", method=RequestMethod.GET)
    public ModelAndView addBookView(Model model) {
        model.addAttribute("licitation", new Licitation());
        return new ModelAndView("add-licitation");
    }

    @RequestMapping(path="/delete_licitation", method=RequestMethod.POST)
    @Transactional
    public RedirectView deleteLicitationSubmit(@ModelAttribute Licitation licitation) throws IllegalAccessException {

        licitationService.deleteLicitation(licitation.getid_licitation());
        return new RedirectView("/all_licitations", true);
    }

    @RequestMapping(path="/delete_licitation", method=RequestMethod.GET)
    public ModelAndView deleteLicitationView(Model model) {
        model.addAttribute("licitation", new Licitation());
        return new ModelAndView("deletelicitation");
    }

    @RequestMapping(path="/add_licitation", method=RequestMethod.POST)
    public RedirectView addBook(@ModelAttribute("licitation") Licitation licitation, RedirectAttributes redirectAttributes, Principal principal) throws IllegalAccessException {
        final RedirectView redirectView = new RedirectView("/all_licitations", true);
        licitation.setStatus(true);
        LocalDateTime time = LocalDateTime.now().plusHours(6);
        licitation.setDeadline(time);
        licitationService.addNewLicitation(licitation, principal);

        redirectAttributes.addFlashAttribute("savedLic", licitation);
        redirectAttributes.addFlashAttribute("addLicitationSuccess", true);
        return redirectView;
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
