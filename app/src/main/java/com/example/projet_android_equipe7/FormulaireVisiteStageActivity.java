package com.example.projet_android_equipe7;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FormulaireVisiteStageActivity extends Activity {

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire_visite);

        // gestion des boutons
        Button buttonValider = findViewById(R.id.button2);
        Button buttonAnnuler = findViewById(R.id.button);



        String etudiant = "TEST";
        Intent intent = getIntent();

        if (intent != null) {
            etudiant = intent.getStringExtra("EXTRA_ETUDIANT");
        }

        Toast.makeText(getApplicationContext(), "prout" + etudiant, Toast.LENGTH_LONG).show();
        TextView textEleve = findViewById(R.id.textView3);
        textEleve.setText(etudiant);

        //on place un écouteur dessus:
        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button2:
                        // enregistrer les données dans la base

                            Intent i = new Intent(FormulaireVisiteStageActivity.this, ValidationFormulaireVisiteStageActivity.class);
                            JSONObject json = new JSONObject();

                            try {
                                json.put("ELEVE", "lucas");
                                json.put("ENSEIGNANT", "alexia");
                                json.put("ENTREPRISE", "ECIBAT");
                                json.put("TUTEUR", "sarah");
                                json.put("INTITULE STAGE", "stage");
                                json.put("Date debut", "20/23/26");
                                json.put("Date fin", "22/22/22");
                                json.put("DATE Visite", "22/5/55");
                                json.put("CONDITION", "ceci sont les conditions");
                                json.put("BILAN", "Ceci est le bilan");
                                json.put("RESSOURCE", "ressourcessss");
                                json.put("COMMENTAIRE", "commentaire");
                                json.put("tuteur oral", "true");
                                json.put("prendre un stagiaire ?", "1ere annéee 2 eme année");


                            }catch (JSONException e) {

                            }
                           String  stringJson = json.toString();
                        i.putExtra("EXTRA_JSON", stringJson);
                        startActivityForResult(i, 0);


                        break;
                    case R.id.button:
                        finish();

                        Toast.makeText(getApplicationContext(), "Annulation de la saisie", Toast.LENGTH_LONG).show();
                        finish();
                        break;


                }

            }
        };
        //on affecte l'écouteur aux boutons:
        buttonValider.setOnClickListener(ecouteur1);
        buttonAnnuler.setOnClickListener(ecouteur1);


        final Calendar myCalendarDebut = Calendar.getInstance();
        final Calendar myCalendarFin = Calendar.getInstance();
        final Calendar myCalendarVisite = Calendar.getInstance();
        final EditText edittextDebut = findViewById(R.id.dateDebut);
        final EditText edittextFin = findViewById(R.id.dateFin);
        final EditText edittextVisite = findViewById(R.id.dateVisite);

        edittextFin.requestFocus();
        edittextVisite.requestFocus();
        edittextDebut.requestFocus();

        final DatePickerDialog.OnDateSetListener dateDebut = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendarDebut.set(Calendar.YEAR, year);
                myCalendarDebut.set(Calendar.MONTH, monthOfYear);
                myCalendarDebut.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(edittextDebut, myCalendarDebut);
            }

        };

        final DatePickerDialog.OnDateSetListener dateFin = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendarFin.set(Calendar.YEAR, year);
                myCalendarFin.set(Calendar.MONTH, monthOfYear);
                myCalendarFin.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(edittextFin, myCalendarFin);
            }

        };

        final DatePickerDialog.OnDateSetListener dateVisite = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendarVisite.set(Calendar.YEAR, year);
                myCalendarVisite.set(Calendar.MONTH, monthOfYear);
                myCalendarVisite.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(edittextVisite, myCalendarVisite);
            }

        };

        edittextDebut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(FormulaireVisiteStageActivity.this, dateDebut, myCalendarDebut.get(Calendar.YEAR), myCalendarDebut.get(Calendar.MONTH), myCalendarDebut.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        edittextFin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(FormulaireVisiteStageActivity.this, dateFin, myCalendarFin.get(Calendar.YEAR), myCalendarFin.get(Calendar.MONTH), myCalendarFin.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        edittextVisite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(FormulaireVisiteStageActivity.this, dateVisite, myCalendarVisite.get(Calendar.YEAR), myCalendarVisite.get(Calendar.MONTH), myCalendarVisite.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }
        private void updateLabel (EditText edittext, Calendar myCalendar){
            String myFormat = "dd/MM/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            edittext.setText(sdf.format(myCalendar.getTime()));


    }
}
