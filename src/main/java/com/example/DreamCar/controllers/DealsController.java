package com.example.DreamCar.controllers;

import com.example.DreamCar.Services.DealsService;
import com.example.DreamCar.Services.LicitationService;
import com.example.DreamCar.models.AppUser;
import com.example.DreamCar.models.Deal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@RestController
public class DealsController {

    private final DealsService dealsService;
    private final LicitationService licitationService;

    @Autowired
    public DealsController(DealsService dealsService, LicitationService licitationService) {
        this.dealsService = dealsService;
        this.licitationService = licitationService;
    }

    @GetMapping
    public ModelAndView FrontPage(Model model, @AuthenticationPrincipal AppUser user) {
        return new ModelAndView("/desprePage");
    }

    @Transactional
    @PutMapping(path = "update/")
    public ResponseEntity<String> updateDeal(@RequestBody Deal deal, @AuthenticationPrincipal AppUser user) {
     if(dealsService.updateDealPrice(deal)){
         return ResponseEntity.accepted().body("Deal updated succesfully!");
    }
    else{
         return ResponseEntity.accepted().body("Deal with spcific ID does not exists!");
     }
    }

    @RequestMapping(path="/all_deals", method=RequestMethod.GET)
    public ModelAndView allDealsView(Model model) {
        long Long = 0;
        model.addAttribute("dealList", dealsService.getDeals());
        return new ModelAndView("/alldeals");
    }

    @RequestMapping(path="/update_deal", method=RequestMethod.GET)
    public ModelAndView updateView(Model model) {
        model.addAttribute("deal", new Deal());
        return new ModelAndView("/updatedeal");
    }

    @Transactional
    @RequestMapping(path="/update_deal", method=RequestMethod.POST)
    public RedirectView updatedealSubmit(@ModelAttribute Deal deal, Principal principal) {
        deal.setUsername(principal.getName());
        dealsService.updateDealPrice(deal);
        return new RedirectView("/all_deals", true);
    }

    @RequestMapping(path="/add_deal", method=RequestMethod.GET)
    public ModelAndView addDealView(Model model) {
        long Long = 0;
        model.addAttribute("deal", new Deal());
        return new ModelAndView("addOferta");
    }

    @RequestMapping(path="/delete_deal", method=RequestMethod.POST)
    @Transactional
    public RedirectView deletedealSubmit(@ModelAttribute Deal deal, Principal principal) throws IllegalAccessException {
        deal.setUsername(principal.getName());
        dealsService.deleteDeal(deal.getIdDeals(),principal);
        return new RedirectView("/all_deals", true);
    }

    @RequestMapping(path="/delete_deal", method=RequestMethod.GET)
    public ModelAndView deleteDealView(Model model) {
        Integer id=0;
        model.addAttribute("deal", new Deal());
        return new ModelAndView("deletedeal");
    }

    @RequestMapping(path="/add_deal", method=RequestMethod.POST)
    public RedirectView addBook(@ModelAttribute("deal") Deal deal,RedirectAttributes redirectAttributes, Principal principal) throws IllegalAccessException {
        final RedirectView redirectView = new RedirectView("/all_deals", true);
        dealsService.addNewDeal(deal.getIdLicitation(), deal, principal);
        return redirectView;
    }



//    public ResponseEntity<String> updateDealPrice(@RequestBody Deal deal, @PathVariable("dealsId") Long id_deal) throws IllegalAccessException {
//        return dealsService.updateDealPrice(deal, id_deal);
//    }

    @GetMapping(path="me/")
    public List<Deal>getMyDeals(Principal principal){
        return dealsService.viewUserDeals(principal);
    }

    @PostMapping(path="{licitationId}")
    public void registerNewDeal(@RequestBody Deal deal,@PathVariable("licitationId") Long licitationId, Principal principal) throws IllegalAccessException {
        dealsService.addNewDeal(licitationId, deal, principal);
    }

    @DeleteMapping(path="delete/{dealsId}/")
    public void deleteLicitation(@PathVariable("dealsId") Long dealsId,  Principal principal) throws IllegalAccessException {
        dealsService.deleteDeal(dealsId, principal);
    }
}
