package com.example.projet_android_equipe7.modele.metier;

public class Entreprise {
    private String idEntreprise;
    private String nom;
    private String codePostal;
    private String rue;
    private String numeroTelephone;
    private String mail;

    public Entreprise(String idEntreprise, String nom, String codePostal, String rue, String numeroTelephone, String mail) {
        this.idEntreprise = idEntreprise;
        this.nom = nom;
        this.codePostal = codePostal;
        this.rue = rue;
        this.numeroTelephone = numeroTelephone;
        this.mail = mail;
    }

    public String getIdEntreprise() {
        return idEntreprise;
    }

    public String getNom() {
        return nom;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public String getRue() {
        return rue;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public String getMail() {
        return mail;
    }
}
