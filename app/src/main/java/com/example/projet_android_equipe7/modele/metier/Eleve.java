package com.example.projet_android_equipe7.modele.metier;

/**
 * @author Crépilière
 * @version 1.0
 */
public class Eleve {
    private String idEleve;
    private String nom;
    private String prenom;
    private String classe;
    private String numeroTelephone;
    private String annee;

    public Eleve(String idEleve, String nom, String prenom, String classe, String numeroTelephone, String annee) {
        this.idEleve = idEleve;
        this.nom = nom;
        this.prenom = prenom;
        this.classe = classe;
        this.numeroTelephone = numeroTelephone;
        this.annee = annee;
    }

    public String getIdEleve() {
        return idEleve;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getClasse() {
        return classe;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public String getAnnee() {
        return annee;
    }

    @Override
    public String toString() {
        return "ELEVE{" +
                "idEleve='" + idEleve + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", classe='" + classe + '\'' +
                ", numeroTelephone='" + numeroTelephone + '\'' +
                ", annee='" + annee + '\'' +
                '}';
    }
}
