package com.example.DreamCar.models;

import javax.persistence.*;

@Entity(name="Deals")
@Table
public class Deal {
    @Id
    @SequenceGenerator(
            name="deals_sequence",
            sequenceName="deals_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private long idDeals;
    private double price;
    private long idLicitation;

    public long getIdLicitation() {
        return idLicitation;
    }

    public void setIdLicitation(long idLicitation) {
        this.idLicitation = idLicitation;
    }

    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private Licitation licitation;




    public Deal(){

    }

    @Override
    public String toString() {
        return "Deals{" +
                "idDeals=" + idDeals +
                "username=" + username +
                ", price=" + price +
                ", licitationId=" + idLicitation +
                '}';
    }

    public Deal(double price) {
        this.price = price;

    }
    public Deal(double price, long idLicitation) {
        this.price = price;
        this.idLicitation = idLicitation;

    }

    public Deal(Long Id, double price, long idLicitation) {
        this.idDeals = Id;
        this.price = price;
        this.idLicitation = idLicitation;

    }


    public long getIdDeals() {
        return idDeals;
    }

    public void setIdDeals(long idDeals) {
        this.idDeals = idDeals;
    }

    public Deal(double price, Licitation licitation, String username, Long idLicitation) {
        this.price = price;
        this.idLicitation = idLicitation;
        this.licitation = licitation;
        this.username = username;
    }

    public Deal(Long id, double price, Licitation licitation, String username, Long idLicitation) {
        this.idDeals = id;
        this.price = price;
        this.idLicitation = idLicitation;
        this.licitation = licitation;
        this.username = username;

    }

    public Deal(double price, Long idLicitation, String username) {
        this.price = price;
        this.idLicitation = idLicitation;
        this.username = username;

    }

    public void setidDeals(long idDeals) {
        this.idDeals = idDeals;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getidDeals() {
        return idDeals;
    }

    public double getPrice() {
        return price;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

}


