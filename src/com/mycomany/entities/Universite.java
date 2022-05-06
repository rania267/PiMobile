/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author Asus
 */
public class Universite {
    private int iduni;
    private String nom;
    private String email;
    private String adresse;
    private String imguni;
    private String mdpuni;

    public Universite() {
    }

    public Universite(int iduni, String nom, String email, String adresse, String imguni, String mdpuni) {
        this.iduni = iduni;
        this.nom = nom;
        this.email = email;
        this.adresse = adresse;
        this.imguni = imguni;
        this.mdpuni = mdpuni;
    }

    public int getIduni() {
        return iduni;
    }

    public void setIduni(int iduni) {
        this.iduni = iduni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getImguni() {
        return imguni;
    }

    public void setImguni(String imguni) {
        this.imguni = imguni;
    }

    public String getMdpuni() {
        return mdpuni;
    }

    public void setMdpuni(String mdpuni) {
        this.mdpuni = mdpuni;
    }


}
