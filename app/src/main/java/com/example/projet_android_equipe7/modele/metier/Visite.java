package com.example.projet_android_equipe7.modele.metier;

/**
 * @author Crépilière
 * @version 1.0
 */
public class Visite {
    private String idVisite;
    private String dateVisite;
    private String compteRenduVisite;
    private String conditionStage;
    private String ressourceStage;

    public Visite(String idVisite, String dateVisite, String compteRenduVisite, String conditionStage, String ressourceStage) {
        this.idVisite = idVisite;
        this.dateVisite = dateVisite;
        this.compteRenduVisite = compteRenduVisite;
        this.conditionStage = conditionStage;
        this.ressourceStage = ressourceStage;
    }

    public String getIdVisite() {
        return idVisite;
    }

    public String getDateVisite() {
        return dateVisite;
    }

    public String getCompteRenduVisite() {
        return compteRenduVisite;
    }

    public String getConditionStage() {
        return conditionStage;
    }

    public String getRessourceStage() {
        return ressourceStage;
    }

    @Override
    public String toString() {
        return "Visite{" +
                "idVisite='" + idVisite + '\'' +
                ", dateVisite='" + dateVisite + '\'' +
                ", compteRenduVisite='" + compteRenduVisite + '\'' +
                ", conditionStage='" + conditionStage + '\'' +
                ", ressourceStage='" + ressourceStage + '\'' +
                '}';
    }
}
