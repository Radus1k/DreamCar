package com.example.DreamCar.controllers;

import com.example.DreamCar.Services.DealsService;
import com.example.DreamCar.Services.LicitationService;
import com.example.DreamCar.models.Deal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path="dreamcar/deals/")
public class DealsController {

    private final DealsService dealsService;
    private final LicitationService licitationService;

    @Autowired
    public DealsController(DealsService dealsService, LicitationService licitationService) {
        this.dealsService = dealsService;
        this.licitationService = licitationService;
    }

    @GetMapping
    public List<Deal> getDeals() {
        return dealsService.getDeals();
    }

    @Transactional
    @PutMapping(path = "update/")
    public ResponseEntity<String> updateDeal(@RequestBody Deal deal) {
     if(dealsService.updateDealPrice(deal)){
         return ResponseEntity.accepted().body("Deal updated succesfully!");
    }
    else{
         return ResponseEntity.accepted().body("Deal with spcific ID does not exists!");
     }


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
