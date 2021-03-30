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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MaRequestEleve {

    private Context context;
    private RequestQueue queue;

    public MaRequestEleve(Context context, RequestQueue queue) {
        this.context = context;
        this.queue = queue;
    }

    /**
     *
     * @param idEleve
     * @param callback
     */
    public void getEleve(final String idEleve, final getEleveCallBack callback){
        String url = "https://www.tartie.fr/projetEquipe7/getEleve.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject json =null;
                try {
                    json =  new JSONObject(response.toString());
                    String error = json.getString("error");
                    if (error.equals("false")){
                        //créer un nouvel Eleve
                        String id = json.getString("idEleve");
                        String nom = json.getString("NOM");
                        String prenom = json.getString("PRENOM");
                        String classe = json.getString("CLASSE");
                        String NUMEROTELEPHONE = json.getString("NUMEROTELEPHONE");
                        String ANNEE = json.getString("ANNEE");
                        Log.d("test apres recuperation des données", ANNEE.toString());
                        Eleve nouvelEleve = new Eleve(id,nom,prenom,classe,NUMEROTELEPHONE,ANNEE);

                        callback.onSuccess(nouvelEleve);
                    } else {
                        callback.onError(json.getString("message"));
                    }
                } catch (JSONException e) {
                    //e.printStackTrace();
                    callback.onError(response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof NetworkError){
                    callback.onError("impossible de se connecter");
                } else if(error instanceof VolleyError){
                    callback.onError("Une erreur s'est produite");
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("IDELEVE",idEleve);
                map.put("error","false");
                map.put("message","message");
                return map;
            }

        };

        queue.add(request);
    }

    public void getAllElevefinal(final getEleveCallBack callback){
        String url = "https://www.tartie.fr/projetEquipe7/getAllEleve.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject json =null;
                JSONObject eleveJson = null;

                try {
                    ArrayList<Eleve> Eleves = new ArrayList<Eleve>();  //crée une nouvelle ArrayList vide pour les éléves les éléves
                    json = new JSONObject(response); //initialise l'objet json a partir de la reponse

                    String error = json.getString("error");
                    if (error.equals("false")){
                        int rowCount = Integer.valueOf(json.getString("rowCount"));
                        for (int i = 0;i < rowCount;i++){

                            eleveJson = new JSONObject(json.getString(String.valueOf(i)));

                            //crée un nouvel eleve basé sur le row recuperé
                            String id = eleveJson.getString("IDELEVE");
                            String nom = eleveJson.getString("NOM");
                            String prenom = eleveJson.getString("PRENOM");
                            String classe = eleveJson.getString("CLASSE");
                            String NUMEROTELEPHONE = eleveJson.getString("NUMEROTELEPHONE");
                            String ANNEE = eleveJson.getString("ANNEE");
                            Eleve nouvelEleve = new Eleve(id,nom,prenom,classe,NUMEROTELEPHONE,ANNEE);
                            Eleves.add(nouvelEleve); //ajoute l'eleve dans la ArrayList
                        }
                        callback.onSuccess(Eleves);
                    } else {
                        callback.onError(json.getString("erreur"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.onError(response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof NetworkError){
                    callback.onError("impossible de se connecter");
                } else if(error instanceof VolleyError){
                    callback.onError("Une erreur s'est produite");
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("error","false");
                map.put("message","message");
                return map;
            }

        };

        queue.add(request);
    }

    public interface getEleveCallBack{
        void onSuccess(ArrayList<Eleve> eleves);
        void onSuccess(Eleve eleve);
        void onError(String message);
    }
}
