package com.example.projet_android_equipe7.modele.dao;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

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
    public void connexion(String login, String password){
        String url = "http://tartie.fr/projetEquipe7/connexion.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("APP",response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("APP",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return  super.getParams();
            }
        };

        queue.add(request);
    }
}
