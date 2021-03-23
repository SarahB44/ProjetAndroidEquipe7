package com.example.projet_android_equipe7.modele.metier;

/**
 * @author Crépilière
 * @version 1.0
 */
public class Tuteur {
    private String idTuteur;
    private String nom;
    private String prenom;
    private String email;
    private String numeroTelephone;
    private Entreprise entreprise;

    public Tuteur(String idTuteur, String nom, String prenom, String email, String numeroTelephone,Entreprise entreprise) {
        this.idTuteur = idTuteur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numeroTelephone = numeroTelephone;
    }

    public String getIdTuteur() {
        return idTuteur;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public Entreprise getEntreprise(){
        return entreprise;
    }

    @Override
    public String toString() {
        return "Tuteur{" +
                "idTuteur='" + idTuteur + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", numeroTelephone='" + numeroTelephone + '\'' +
                ", entreprise=" + entreprise.toString() +
                '}';
    }
}
