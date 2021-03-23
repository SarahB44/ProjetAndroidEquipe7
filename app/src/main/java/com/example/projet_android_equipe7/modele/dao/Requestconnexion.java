package com.example.projet_android_equipe7.modele.dao;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Requestconnexion {

    private Context context;
    private RequestQueue queue;

    public Requestconnexion(Context context, RequestQueue queue) {
        this.context = context;
        this.queue = queue;
    }

    /**
     *
     * @param login
     * @param password
     */
    public void connexion(final String login, final String password, final LoginCallBack callback){
        String url = "https://www.tartie.fr/projetEquipe7/connexion.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject json =null;

                //Log.d("Test récuperation de la réponse",response);
                try {

                    json =  new JSONObject(response.toString());
                    String error = json.getString("error");

                    if (error == "true"){
                        //créer un nouvel user
                        //
                        //String id = json.getString("IDADMIN");
                        //String nom = json.getString("nom");

                        //callback.onSuccess(id.toString(),nom.toString());
                        callback.onError(json.getString("message"));

                    } else {
                        String id = json.getString("IDADMIN");
                        String nom = json.getString("nom");

                        callback.onSuccess(id.toString(),nom.toString());
                        //callback.onError(json.getString("message"));
                    }


                } catch (JSONException e) {
                    //e.printStackTrace();
                    callback.onError("Mot de passe ou identifiant incorrect.");
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
                map.put("login",login);
                map.put("password",password);
                map.put("error","false");
                map.put("message","message");
                return map;

            }

        };

        queue.add(request);
    }

    public interface LoginCallBack{
        void onSuccess(String id, String nom);
        void onError(String message);
    }

}