package com.example.projet_android_equipe7;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.projet_android_equipe7.modele.dao.MaRequestEleve;
import com.example.projet_android_equipe7.modele.dao.Requestconnexion;
import com.example.projet_android_equipe7.modele.metier.Eleve;

import org.json.JSONException;

import java.util.ArrayList;

public class SelectionEtudiantActivity extends Activity {
    final String[] lEtudiant= new String[1];
    private RequestQueue queue;
    private Requestconnexion request;
    private Handler handler;

    ArrayList<String> lesetudiants = new ArrayList<String>();

    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_etudiant);

        Button buttonAfficherVisiteStage = findViewById(R.id.buttonAfficherVisiteStage);
        //on place un écouteur dessus:
        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {

                        //on passer les infos dans l'autre interface

                            Intent i = new Intent(SelectionEtudiantActivity.this, FormulaireVisiteStageActivity.class);

                            i.putExtra("EXTRA_ETUDIANT", lEtudiant[0]);
                            startActivityForResult(i, 0);
                }

            };

        //on affecte l'écouteur aux boutons:
        buttonAfficherVisiteStage.setOnClickListener(ecouteur1);


        //gestion de la liste déroulante des Etudiants
        final Spinner spinnerListeEtudiant = (Spinner) findViewById(R.id.spinnerEtudiant);
        /*
        GESTION DE LA BDD
        */

        //gere le singleton
        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new Requestconnexion(this,queue);
        handler = new Handler();

        final MaRequestEleve test = new MaRequestEleve(this,queue);
        test.getAllElevefinal(new MaRequestEleve.getEleveCallBack() {
            @Override
            public void onSuccess(ArrayList<Eleve> eleves) {
                Log.d("test recuperation eleves",eleves.toString());
                for(int i=0; i<eleves.size();i++){
                    Eleve eleve = eleves.get(i);
                    lesetudiants.add(eleve.getNom() + " " + eleve.getPrenom());

                }
                SelectionEtudiantActivity.this.runOnUiThread(new Runnable(){
                    @Override
                    public void run(){

                            ArrayAdapter<String> dataAdapterR = new ArrayAdapter<String>(SelectionEtudiantActivity.this, android.R.layout.simple_spinner_item, lesetudiants);
                            dataAdapterR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerListeEtudiant.setAdapter(dataAdapterR);
                            spinnerListeEtudiant.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    lEtudiant[0] = String.valueOf(spinnerListeEtudiant.getSelectedItem());
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });



                        }
                    });

            }

            @Override
            public void onSuccess(Eleve nouvelEleve) {

            }

            @Override
            public void onError(String message) {
                //Toast.makeText(getBaseContext(),message,Toast.LENGTH_LONG).show();
                Log.d("Rapport d'erreur",message);
            }
        });


        //TEMPORAIRE AVANT LA GESTION DE LA BDD




    }
}
