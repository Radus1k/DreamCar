package com.example.DreamCar.Services;

import com.example.DreamCar.email.EmailSender;
import com.example.DreamCar.models.ConfirmationToken;
import com.example.DreamCar.models.Deal;
import com.example.DreamCar.models.Licitation;
import com.example.DreamCar.repositories.LicitationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Scheduled;
import com.example.DreamCar.email.EmailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LicitationService {

    private final LicitationRepository licitationRepository;
    private final EmailSender emailSender;

    @Autowired
    public LicitationService(LicitationRepository studentRepository, AppUserService appUserService) {
        this.licitationRepository = studentRepository;
        emailSender=null;
    }

    public List<Licitation> getActiveLicitations() {
        checkLicitations();
        return licitationRepository.findAllActiveLicitations();
    }

    public List<Licitation> getAllLicitations() {
        return licitationRepository.findAll();
    }


    public Licitation getLicitationById(long id) {
        checkLicitations();
        return licitationRepository.getById(id);
    }

    public void addNewLicitation(Licitation licitation, Principal principal) throws IllegalAccessException {
        if (!principal.getName().equals("admin@test.net")) {
            System.out.println(principal.getName());
            throw new IllegalAccessException(
                    "Only admins can add new licitations!");
        }
        licitationRepository.save(licitation);
    }

    public Licitation getById(Long id){
        boolean lic_exists = licitationRepository.existsById(id);
        if(!lic_exists) {
            return licitationRepository.getById(id);
        }
        return null;
    }

    public void deleteLicitation(Long licitationId) throws IllegalAccessException {

        boolean lic_exists = licitationRepository.existsById(licitationId);
        if (!lic_exists) {
            throw new IllegalAccessException(
                    "Licitation with Id: " + licitationId + " doesn't exists!");
        }
        licitationRepository.deleteById(licitationId);
    }

    public void addDealForLicitation(Long licitationId, Deal deal) throws IllegalAccessException {
        boolean lic_exists = licitationRepository.existsById(licitationId);
        if (!lic_exists) {
            throw new IllegalAccessException(
                    "Licitation with Id: " + licitationId + " doesn't exists!");
        }
        Licitation licitation = licitationRepository.getById(licitationId);
        licitation.addDealToList(deal);
        licitationRepository.save(licitation);
    }

    public void setWinner(Long licitationId, String winner) throws IllegalAccessException {
        boolean lic_exists = licitationRepository.existsById(licitationId);
        if (!lic_exists) {
            throw new IllegalAccessException(
                    "Licitation with Id: " + licitationId + " doesn't exists!");
        }
        Licitation licitation = licitationRepository.getById(licitationId);
        licitation.setWinner(winner);
        licitation.setStatus(false);
        licitationRepository.save(licitation);
    }


    public boolean updateTargetPrice(Licitation licitation) {
        if (!licitationRepository.existsById(licitation.getid_licitation())) {
            return false;
        }
        else{
            licitationRepository.updateLicitationTargetPrice(licitation.getTargetPrice(), licitation.getid_licitation());
            licitationRepository.save(licitation);
            return true;
        }
    }
    

    //TODO: SCHEDULED TASK
    @Transactional
    @Scheduled(fixedRate=10000)//  10 secunde
    public void checkLicitations() {
        try {
            System.out.println("Checking expired licitations");
            List<Licitation> licitationList = licitationRepository.findAll();
            System.out.print(licitationList.size());
            if (licitationList.size() > 0) {
                for (Licitation lic : licitationList) {
                    if (lic.getDeadline().isBefore(LocalDateTime.now())) {
                        lic.setStatus(false);// LICITATIA A EXPIRAT!
                        licitationRepository.save(lic);
                    }
                }
            }
            else{
                System.out.println("Empty list of licitations!");
            }
        }
        catch(Exception e ){
           System.out.println(e.toString());
        }
    }
}
