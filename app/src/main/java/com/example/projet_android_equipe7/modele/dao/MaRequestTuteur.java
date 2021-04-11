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
import com.example.projet_android_equipe7.modele.metier.Entreprise;
import com.example.projet_android_equipe7.modele.metier.Tuteur;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MaRequestTuteur {

    private static Context context;
    private static RequestQueue queue;

    public MaRequestTuteur(Context context, RequestQueue queue) {
        this.context = context;
        this.queue = queue;
    }

    /**
     * @copie de getEleve
     * @param idTuteur
     * @param callback
     */
    public static void getTuteur(final String idTuteur, final getTuteurCallBack callback){
        String url = "https://www.tartie.fr/projetEquipe7/getTuteur.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject json =null;
                try {
                    json =  new JSONObject(response.toString());
                    String error = json.getString("error");
                    if (error.equals("false")){
                        //créer un nouveau Tuteur
                        final String id = json.getString("IDTUTEUR");
                        String idEntreprise = json.getString("IDENTREPRISE");
                        final String nom = json.getString("NOM");
                        final String prenom = json.getString("PRENOM");
                        final String email = json.getString("EMAIL");
                        final String numerotelephone = json.getString("NUMEROTELEPHONE");

                        Tuteur nouveauTuteur = new Tuteur(id,nom,prenom,email,numerotelephone);
                        callback.onSuccess(nouveauTuteur);
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
                map.put("IDTUTEUR",idTuteur);
                map.put("error","false");
                map.put("message","message");
                return map;
            }

        };

        queue.add(request);
    }


    public interface getTuteurCallBack{
        void onSuccess(Tuteur nouveauTuteur);
        void onError(String message);
    }
}
