package com.example.projet_android_equipe7.modele.dao;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.projet_android_equipe7.modele.metier.Enseignant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MaRequestEnseignant {

    private Context context;
    private RequestQueue queue;

    public MaRequestEnseignant(Context context, RequestQueue queue) {
            this.context = context;
            this.queue = queue;
        }

        /**
         * @copie de getEleve
         * @param idEnseignant
         * @param callback
         */
        public void getEnseignant(final String idEnseignant, final getEnseignantCallBack callback){
            String url = "https://www.tartie.fr/projetEquipe7/getEnseignant.php";

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    JSONObject json = null;
                    try {
                        json = new JSONObject(response.toString());
                        String error = json.getString("error");
                        if (error.equals("false")) {
                            //créer un nouvel Eleve
                            String id = json.getString("IDENSEIGNANT");
                            String nom = json.getString("NOM");
                            String prenom = json.getString("PRENOM");
                            String NUMEROTELEPHONE = json.getString("NUMEROTELEPHONE");
                            String MAIL = json.getString("MAIL");
                            Enseignant nouvelEnseignant = new Enseignant(id, nom, prenom, NUMEROTELEPHONE, MAIL);

                            callback.onSuccess(nouvelEnseignant);
                        } else {
                            callback.onError(json.getString("message"));
                        }
                    } catch (JSONException e) {
                        callback.onError(response.toString() +" "+ e.toString());
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
                    map.put("IDENSEIGNANT", idEnseignant);
                    map.put("error", "false");
                    map.put("message", "message");
                    return map;
                }
            };
            queue.add(request);
        }

    public  interface getEnseignantCallBack{
        void onSuccess(Enseignant nouvelEnseignant);
        void onError(String message);
    }
}
