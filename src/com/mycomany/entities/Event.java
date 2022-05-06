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
public class Event {
   
    private int idevent;
    private String idorg;
    private String description;
    private String date;
    private String heure;
    private String lien;
    private String imgev;
    private int nbrparticipant;
    private int iduni;

    public Event() {
    }

    public Event(int idevent, String idorg, String description, String date, String heure, String lien, String imgev, int nbrparticipant, int iduni) {
        this.idevent = idevent;
        this.idorg = idorg;
        this.description = description;
        this.date = date;
        this.heure = heure;
        this.lien = lien;
        this.imgev = imgev;
        this.nbrparticipant = nbrparticipant;
        this.iduni = iduni;
    }

    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    public String getIdorg() {
        return idorg;
    }

    public void setIdorg(String idorg) {
        this.idorg = idorg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getImgev() {
        return imgev;
    }

    public void setImgev(String imgev) {
        this.imgev = imgev;
    }

    public int getNbrparticipant() {
        return nbrparticipant;
    }

    public void setNbrparticipant(int nbrparticipant) {
        this.nbrparticipant = nbrparticipant;
    }

    public int getIduni() {
        return iduni;
    }

    public void setIduni(int iduni) {
        this.iduni = iduni;
    }

    
    
}
