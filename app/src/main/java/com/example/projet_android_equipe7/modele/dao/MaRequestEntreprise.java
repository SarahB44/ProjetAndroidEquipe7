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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MaRequestEntreprise {
    private Context context;
    private RequestQueue queue;

    public MaRequestEntreprise(Context context, RequestQueue queue) {
        this.context = context;
        this.queue = queue;
    }

    /**
     *
     * @param idEntreprise
     * @param callback
     */
    public void getEleve(final String idEntreprise, final getEntrepriseCallBack callback){
        String url = "https://www.tartie.fr/projetEquipe7/getEntreprise.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject json =null;
                try {
                    json =  new JSONObject(response.toString());
                    String error = json.getString("error");
                    if (error.equals("false")){
                        //créer un nouvel Eleve
                        String id = json.getString("IDENTREPRISE");
                        String nom = json.getString("NOM");
                        String codePostal = json.getString("CODEPOSTAL");
                        String ville = json.getString("VILLE");
                        String rue = json.getString("RUE");
                        String numerotelephone = json.getString("NUMEROTELEPHONE");
                        String mail = json.getString("MAIl");

                        Entreprise nouvelEntreprise = new Entreprise(id,nom, codePostal, ville, rue, numerotelephone,mail);

                        callback.onSuccess(nouvelEntreprise);
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
                map.put("IDENTREPRISE",idEntreprise);
                map.put("error","false");
                map.put("message","message");
                return map;
            }

        };

        queue.add(request);
    }


    public interface getEntrepriseCallBack{
        void onSuccess(Entreprise nouvelEntreprise);
        void onError(String message);
    }
}
