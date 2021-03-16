package com.example.projet_android_equipe7.modele.metier;

public class Enseignant {
    private String idEnseignant;
    private String nom;
    private String prenom;
    private String numeroTelephone;
    private String mail;

    public Enseignant(String idEnseignant, String nom, String prenom, String numeroTelephone, String mail) {
        this.idEnseignant = idEnseignant;
        this.nom = nom;
        this.prenom = prenom;
        this.numeroTelephone = numeroTelephone;
        this.mail = mail;
    }

    public String getIdEnseignant() {
        return idEnseignant;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public String getMail() {
        return mail;
    }

    @Override
    public String toString() {
        return "Enseignant{" +
                "idEnseignant='" + idEnseignant + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", numeroTelephone='" + numeroTelephone + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
