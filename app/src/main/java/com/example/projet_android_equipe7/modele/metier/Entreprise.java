package com.example.projet_android_equipe7.modele.metier;

/**
 * @author Crépilière
 * @version 1.0
 */
public class Entreprise {
    private String idEntreprise;
    private String nom;
    private String codePostal;
    private String ville;
    private String rue;
    private String numeroTelephone;
    private String mail;

    public Entreprise(String idEntreprise, String nom, String codePostal,String ville, String rue, String numeroTelephone, String mail) {
        this.idEntreprise = idEntreprise;
        this.nom = nom;
        this.ville = ville;
        this.codePostal = codePostal;
        this.rue = rue;
        this.numeroTelephone = numeroTelephone;
        this.mail = mail;
    }

    public String getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(String idEntreprise) {
        this.idEntreprise = idEntreprise;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Entreprise{" +
                "idEntreprise='" + idEntreprise + '\'' +
                ", nom='" + nom + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", rue='" + rue + '\'' +
                ", numeroTelephone='" + numeroTelephone + '\'' +
                ", mail='" + mail + '\'' +
                ", ville '" +ville + '\'' +
                '}';
    }
}
