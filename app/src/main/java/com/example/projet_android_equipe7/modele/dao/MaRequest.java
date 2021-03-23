package com.example.projet_android_equipe7.modele.dao;

import android.app.Activity;
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
import com.example.projet_android_equipe7.ui.login.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MaRequest {

    private Context context;
    private RequestQueue queue;

    public MaRequest(Context context, RequestQueue queue) {
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

                    Log.d("json",json.toString());

                    String error = json.getString("error");
                    if (error.equals("false")){
                        //créer un nouvel Eleve
                        Log.d("r",json.toString());
                        String id = json.getString("idEleve");
                        String nom = json.getString("nom");
                        String prenom = json.getString("prenom");
                        String classe = json.getString("CLASSE");
                        //String NUMEROTELEPHONE = json.getString("NUMEROTELEPHONE");
                        String ANNEE = json.getString("ANNEE");

                        //Eleve nouvelEleve = new Eleve(id,nom,prenom,classe,NUMEROTELEPHONE,ANNEE);

                        //callback.onSuccess(nouvelEleve);
                    } else {
                        callback.onError(json.getString("message"));
                    }
                } catch (JSONException e) {
                    //e.printStackTrace();
                    Log.d("test", e.toString());
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
                Log.d("test","test");

                Map<String,String> map = new HashMap<>();
                map.put("IDELEVE",idEleve);
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

    public  interface  getEleveCallBack{
        void onSuccess(Eleve nouvelEleve);
        void onError(String message);
    }
}
