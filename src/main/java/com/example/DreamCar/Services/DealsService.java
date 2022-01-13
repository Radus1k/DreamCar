package com.example.DreamCar.Services;

import com.example.DreamCar.email.EmailSender;
import com.example.DreamCar.models.Deal;
import com.example.DreamCar.models.Licitation;
import com.example.DreamCar.repositories.DealsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.security.Principal;
import java.util.List;

@Service
public class DealsService {

    private final DealsRepository dealsRepository;
    private final DealsRepository licitationRepository;
    private final LicitationService licitationService;
    private final EmailSender emailSender;



    @Autowired
    public DealsService(DealsRepository dealsRepository, DealsRepository licitationRepository, LicitationService licitationService, EmailSender emailSender) {
        this.dealsRepository = dealsRepository;
        this.licitationRepository = licitationRepository;
        this.licitationService = licitationService;
        this.emailSender = emailSender;
    }

    public boolean updateDealPrice(Deal deal) {
        if (!dealsRepository.existsById(deal.getidDeals())) {
            return false;
        }
        else{
            dealsRepository.updateDealPrice(deal.getPrice(), deal.getidDeals());
            dealsRepository.save(deal);
            return true;
            }
        }

    public List<Deal> getDeals() {
        return dealsRepository.findAll();
    }

    public List<Deal> viewUserDeals(Principal principal) {
        return dealsRepository.findMyDeals(principal.getName());

    }

    public void addNewDeal(Long licitationId, Deal deal_aux, Principal principal) throws IllegalAccessException {
        Licitation licitation = licitationService.getLicitationById(licitationId);
        String username = principal.getName();
        Deal deal = new Deal(deal_aux.getPrice(), licitation, username);
        dealsRepository.save(deal);
        if(deal.getPrice()> licitation.getTargetPrice()){ // DACA CINEVA OFERA PESTE TARGET, LICITATIA SE INCHEIE SI SE ANUNTA CASTIGATORUL
            System.out.println("\n\n\n\n\n WE HAVE A WINNER!!! \n\n\n");;
            licitationService.setWinner(licitationId, deal.getUsername());
            emailSender.send(
                    username,
                    buildEmail(username), "Licitation won!");
            System.out.println(username);
            //TODO: Mail catre castigator prin MAIL!
        }
    }

    public void deleteDeal(Long dealID, Principal principal) throws IllegalAccessException {
        if(!principal.getName().equals("admin")){
            throw new IllegalAccessException(
                    "Only Admin can delete licitations!");
        }

        boolean lic_exists = dealsRepository.existsById(dealID);
        if (!lic_exists) {
            throw new IllegalAccessException(
                    "Deal with Id: " + dealID + " doesn't exists!");
        }
        dealsRepository.deleteById(dealID);
    }

    private String buildEmail(String name) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Licitation won!</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> You won the licitation!" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}
