package com.example.DreamCar.controllers;

import com.example.DreamCar.Services.DealsService;
import com.example.DreamCar.models.AppUser;
import com.example.DreamCar.models.Deal;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    Logger logger = LoggerFactory.getLogger(DealsController.class);

    private final DealsService dealsService;

    public DealsController(DealsService dealsService) {
        this.dealsService = dealsService;
    }
//    private final LicitationService licitationService;
//
//    @Autowired
//    public DealsController(DealsService dealsService, LicitationService licitationService) {
//        this.dealsService = dealsService;
//        this.licitationService = licitationService;
//    }

    @GetMapping
    public ModelAndView FrontPage(Model model, @AuthenticationPrincipal AppUser user) {
        logger.info("Get /desprepage/ method accessed by user: "+ user.getUsername());
        return new ModelAndView("/desprePage");
    }

    @Transactional
    @PutMapping(path = "update/")
    public ResponseEntity<String> updateDeal(@RequestBody Deal deal, @AuthenticationPrincipal AppUser user) {
     if(dealsService.updateDealPrice(deal)){
         logger.info("User: " + user.getUsername() + " updated his deal with id: " + deal.getidDeals());
         return ResponseEntity.accepted().body("Deal updated succesfully!");
    }
    else{
         logger.error("Deal was not updated properly! The ID of passed Deal object might not exist!");
         return ResponseEntity.accepted().body("Deal with spcific ID does not exists!");

     }
    }

    @RequestMapping(path="/all_deals", method=RequestMethod.GET)
    public ModelAndView allDealsView(Model model) {
        long Long = 0;
        model.addAttribute("dealList", dealsService.getDeals());
        logger.info(" all_Deals View GET METHOD called!");
        return new ModelAndView("/alldeals");
    }

    @RequestMapping(path="/update_deal", method=RequestMethod.GET)
    public ModelAndView updateView(Model model) {
        logger.info("update_deal View GET METHOD called!");
        model.addAttribute("deal", new Deal());
        return new ModelAndView("/updatedeal");
    }

    @Transactional
    @RequestMapping(path="/update_deal", method=RequestMethod.POST)
    public RedirectView updatedealSubmit(@ModelAttribute Deal deal, Principal principal) throws IllegalAccessException {
        deal.setUsername(principal.getName());
        try {
            dealsService.updateDealPrice(deal);
        }
        catch(ServiceException serviceException){
            logger.error("Unable to update Deal's Price. Probably given ID not existing. \n TRACEBACKERROR: "+ serviceException.getMessage());
        }
        return new RedirectView("/all_deals", true);
    }

    @RequestMapping(path="/add_deal", method=RequestMethod.GET)
    public ModelAndView addDealView(Model model) {
        long Long = 0;
        model.addAttribute("deal", new Deal());
        logger.info("/add_deal View GET METHOD called!");
        return new ModelAndView("addOferta");
    }

    @RequestMapping(path="/delete_deal", method=RequestMethod.POST)
    @Transactional
    public RedirectView deletedealSubmit(@ModelAttribute Deal deal, Principal principal) throws IllegalAccessException {
        try {
            deal.setUsername(principal.getName());
            dealsService.deleteDeal(deal.getIdDeals(), principal);
            logger.info("User: " + principal.getName() + " Deleted his deal with id: " + deal.getidDeals());
        }
        catch(IllegalAccessException exception){
            logger.error("Cannot delete deal!" + exception.getMessage());
        }
        return new RedirectView("/all_deals", true);
    }

    @RequestMapping(path="/delete_deal", method=RequestMethod.GET)
    public ModelAndView deleteDealView(Model model) {
        logger.info("/delete_deal View GET METHOD called!");
        model.addAttribute("deal", new Deal());
        return new ModelAndView("deletedeal");
    }

    @RequestMapping(path="/add_deal", method=RequestMethod.POST)
    public RedirectView addBook(@ModelAttribute("deal") Deal deal,RedirectAttributes redirectAttributes, Principal principal) throws IllegalAccessException {
        final RedirectView redirectView = new RedirectView("/all_deals", true);
        try {
            dealsService.addNewDeal(deal.getIdLicitation(), deal, principal);
        }
        catch(HibernateException hibernateException){
            logger.error("Could not add new Deal for user: "  + principal.getName() + "\n TRACEBACKERROR: "  + hibernateException.getMessage());
        }
        return redirectView;
    }



//    public ResponseEntity<String> updateDealPrice(@RequestBody Deal deal, @PathVariable("dealsId") Long id_deal) throws IllegalAccessException {
//        return dealsService.updateDealPrice(deal, id_deal);
//    }

    @GetMapping(path="me/")
    public List<Deal>getMyDeals(Principal principal){
        logger.info("/me  View GET METHOD called!");
        return dealsService.viewUserDeals(principal);
    }

    @PostMapping(path="{licitationId}")
    public void registerNewDeal(@RequestBody Deal deal,@PathVariable("licitationId") Long licitationId, Principal principal) throws IllegalAccessException {
        try {
            dealsService.addNewDeal(licitationId, deal, principal);
        }
        catch(ServiceException serviceException){
            logger.error("Could not add new Deal object for user: " + principal.getName()+ "  \n TRACEBACKERROR: " + serviceException.getMessage());
        }
    }

    @DeleteMapping(path="delete/{dealsId}/")
    public void deleteLicitation(@PathVariable("dealsId") Long dealsId,  Principal principal) throws IllegalAccessException {
        try{
            dealsService.deleteDeal(dealsId, principal);
        }
        catch(ServiceException serviceException){
            logger.error("Could not DELETE Deal with ID: " + dealsId + " \n TRACEBACKERROR ERROR: " + serviceException.getMessage());
        }

    }
}
