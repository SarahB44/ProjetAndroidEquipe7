package com.example.projet_android_equipe7.modele.dao;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.projet_android_equipe7.modele.metier.Eleve;
import com.example.projet_android_equipe7.modele.metier.Enseignant;
import com.example.projet_android_equipe7.modele.metier.Entreprise;
import com.example.projet_android_equipe7.modele.metier.Stage;
import com.example.projet_android_equipe7.modele.metier.Tuteur;
import com.example.projet_android_equipe7.modele.metier.Visite;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MaRequestStage {
    private Context context;
    private RequestQueue queue;

    public  MaRequestStage(Context context, RequestQueue queue) {
        this.context = context;
        this.queue = queue;
    }

    /**
     * @copie de getEleve
     * @param idEleve
     * @param callback
     */
    public void getStage(final String idEleve, final MaRequestStage.getStageCallBack callback){
        String url = "https://www.tartie.fr/projetEquipe7/getStage.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject json = null;
                try {
                    json = new JSONObject(response.toString());
                    String error = json.getString("error");
                    if (error.equals("false")) {
                        //créer un nouveau stage
                        String id = json.getString("IDSTAGE");
                        String intitule = json.getString("INTITULE");
                        String dateDebut = json.getString("DATEDEBUT");
                        String dateFin = json.getString("DATEFIN");

                        //crée un nouvel Eleve
                        String idEleve = json.getString("IDELEVE");
                        String nomEleve = json.getString("ELEVE_NOM");
                        String prenomEleve = json.getString("ELEVE_PRENOM");
                        String classe = json.getString("CLASSE");
                        String numerotelephone = json.getString("ELEVE_NUMEROTELEPHONE");
                        String anneeEleve = json.getString("ELEVE_ANNEE");

                        Eleve unEleve = new Eleve(idEleve,nomEleve,prenomEleve,classe,numerotelephone,anneeEleve);

                        //Crée un nouvel Enseignant
                        String idEnseignant = json.getString("IDENSEIGNANT");
                        String nomEnseignant = json.getString("ENSEIGNANT_NOM");
                        String prenomEnseignant = json.getString("ENSEIGNANT_PRENOM");
                        String numerotelephoneEnseignant = json.getString("ENSEIGNANT_NUMEROTELEPHONE");
                        String mailEnseignant = json.getString("ENSEIGNANT_MAIL");

                        Enseignant unEnseignant = new Enseignant(idEnseignant,nomEnseignant,prenomEnseignant,numerotelephoneEnseignant,mailEnseignant);

                        //Crée une nouvel entreprise
                        String identreprise = json.getString("IDENTREPRISE");
                        String entrepriseNom = json.getString("ENTREPRISE_NOM");
                        String entrepriseCodepostal = json.getString("ENTREPRISE_CODEPOSTAL");
                        String entrepriseVille = json.getString("ENTREPRISE_VILLE");
                        String entrepriseRue = json.getString("ENTREPRISE_RUE");
                        String entrepriseNumerotelephone = json.getString("ENTREPRISE_NUMEROTELEPHONE");
                        String entrepriseMail = json.getString("ENTREPRISE_MAIL");

                        Entreprise uneEntreprise = new Entreprise(identreprise,entrepriseNom,entrepriseCodepostal,entrepriseVille,entrepriseRue,entrepriseNumerotelephone,entrepriseMail);

                        //crée un nouveau tuteur
                        String idtuteur = json.getString("IDTUTEUR");
                        String tuteurNom = json.getString("TUTEUR_NOM");
                        String tuteurPrenom = json.getString("TUTEUR_PRENOM");
                        String tuteurEmail = json.getString("TUTEUR_EMAIL");
                        String tuteurNumerotelephone = json.getString("TUTEUR_NUMEROTELEPHONE");

                        Tuteur unTuteur = new Tuteur(idtuteur,tuteurNom,tuteurPrenom,tuteurEmail,tuteurNumerotelephone);


                        Stage unStage = new Stage(id,intitule,dateDebut,dateFin,unEleve,unEnseignant,uneEntreprise,unTuteur);
                        callback.onSuccess(unStage);
                    } else {
                        callback.onError(json.getString("message"));
                    }
                } catch (JSONException e) {
                    callback.onError(e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    callback.onError("impossible de se connecter");
                } else if (error instanceof VolleyError) {
                    callback.onError("Une erreur s'est produite");
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("IDELEVE", idEleve);
                map.put("error", "false");
                map.put("message", "message");
                return map;
            }
        };
        queue.add(request);
    }

    public  interface getStageCallBack{
        void onSuccess(Stage unStage);
        void onError(String message);
    }
}
