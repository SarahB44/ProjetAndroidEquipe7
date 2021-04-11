package com.example.projet_android_equipe7.modele.metier;

/**
 * @author Crépilière
 * @version 1.0
 */
public class Stage {
    private String idStage;
    private String intitule;
    private String dateDebut;
    private String dateFin;
    private Eleve eleve;
    private Enseignant enseignant;
    private Entreprise entreprise;
    private Tuteur tuteur;
    private Visite visite;

    public Stage(String idStage, String intitule, String dateDebut, String dateFin, Eleve eleve, Enseignant enseignant, Entreprise entreprise, Tuteur tuteur) {
        this.idStage = idStage;
        this.intitule = intitule;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.eleve = eleve;
        this.enseignant = enseignant;
        this.entreprise = entreprise;
        this.tuteur = tuteur;
    }

    public String getIdStage() {
        return idStage;
    }

    public String getIntitule() {
        return intitule;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public Tuteur getTuteur() {
        return tuteur;
    }

    @Override
    public String toString() {
        return "Stage{" +
                "idStage='" + idStage + '\'' +
                ", intitule='" + intitule + '\'' +
                ", dateDebut='" + dateDebut + '\'' +
                ", dateFin='" + dateFin + '\'' +
                ", eleve="+this.eleve.toString() + '\'' +
                '}';
    }
}
