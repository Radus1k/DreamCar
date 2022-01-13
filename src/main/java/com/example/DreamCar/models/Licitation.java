package com.example.DreamCar.models;

import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="Licitation")
public class Licitation {

    @Column
    @SequenceGenerator(
            name="licitation_sequence",
            sequenceName="licitation_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "licitation_sequence"
    )
    @Id
    private Long id_licitation;
    private String category;
    private Integer cantity;
    private LocalDateTime deadline;
    private double targetPrice; // Pretul la care produsul s-a vandut
    private boolean status; // Licitatie terminata sau in curs. ->false pt terminata, true-> pt in desfasurare
    private String winner;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "licitation")
    private List<Deal> dealList;


    public Licitation(){

    }

    public Licitation(Long id_licitation, String category, Integer cantity, LocalDateTime deadline, double targetPrice, boolean status) {
        this.id_licitation = id_licitation;
        this.category = category;
        this.cantity = cantity;
        this.deadline = deadline;
        this.targetPrice = targetPrice;
        this.status = status;
    }

    public Licitation(Long id_licitation, String category, Integer cantity, LocalDateTime deadline, double targetPrice, boolean status,List<Deal> dealList) {
        this.id_licitation = id_licitation;
        this.category = category;
        this.cantity = cantity;
        this.deadline = deadline;
        this.targetPrice = targetPrice;
        this.status = status;
        this.dealList = dealList;
    }
    public Licitation(Long id_licitation, String category, Integer cantity, LocalDateTime deadline, double targetPrice, boolean status,List<Deal> dealList, String winner) {
        this.id_licitation = id_licitation;
        this.category = category;
        this.cantity = cantity;
        this.deadline = deadline;
        this.targetPrice = targetPrice;
        this.status = status;
        this.dealList = dealList;
        this.winner=winner;
    }



    public Licitation( String category, Integer cantity, LocalDateTime deadline, double targetPrice, boolean status) {
        this.category = category;
        this.cantity = cantity;
        this.deadline = deadline;
        this.targetPrice = targetPrice;
        this.status = status;
    }


    @Override
    public String toString() {
        return "Licitation{" +
                "id_licitation=" + id_licitation +
                ", category='" + category + '\'' +
                ", cantity=" + cantity +
                ", deadline=" + deadline +
                ", targetPrice=" + targetPrice +
                ", status=" + status +
                ", winner=" + winner +
                ", dealList=" + dealList.toString() +
                '}';
    }



    public void viewDealsList(){
        System.out.println("Deals list: ");
        System.out.println(this.dealList);

    }
    public void addDealToList(Deal deal){
        this.dealList.add(deal);
    }

    public List<Deal> getDealsList() {
        return dealList;
    }

    public Long getid_licitation() {
        return id_licitation;
    }

    public String getCategory() {
        return category;
    }

    public Integer getCantity() {
        return cantity;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public double getTargetPrice() {
        return targetPrice;
    }

    public boolean isStatus() {
        return status;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public List<Deal> getDealList() {
        return dealList;
    }
    public void setDealsList(List<Deal> dealList) {
        this.dealList = dealList;
    }


    public void setid_licitation(Long id_licitation) {
        this.id_licitation = id_licitation;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCantity(Integer cantity) {
        this.cantity = cantity;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public void setTargetPrice(double targetPrice) {
        this.targetPrice = targetPrice;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

