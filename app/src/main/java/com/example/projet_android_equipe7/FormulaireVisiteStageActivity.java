package com.example.projet_android_equipe7;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.app.Activity;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.projet_android_equipe7.modele.dao.MaRequestStage;
import com.example.projet_android_equipe7.modele.dao.Requestconnexion;
import com.example.projet_android_equipe7.modele.metier.Stage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FormulaireVisiteStageActivity extends Activity {

    final Context context = this;
    private RequestQueue queue;
    private Requestconnexion request;
    private Handler handler;

    final String[] jury= new String[1];
    final String[] checkbox =new String[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire_visite);

        // gestion des boutons
        Button buttonValider = findViewById(R.id.button2);
        Button buttonAnnuler = findViewById(R.id.button);



        String etudiant = "TEST"; //instanciation du nom de l'etudiant avec la valeur TEST
        String id = "";
        Intent intent = getIntent(); //recupere les valeurs de l'intent

        //si l'intent n'est pas vide
        if (intent != null) {
            id = intent.getStringExtra("EXTRA_ID_ETUDIANT");
            etudiant = intent.getStringExtra("EXTRA_ETUDIANT"); //recupere les valeurs de l'etudiants
        } else {
            finish(); //termine l'activité
        }

        /* BDD */

        //gere le singleton
        queue = VolleySingleton.getInstance(this).getRequestQueue();

        MaRequestStage requestStage = new MaRequestStage(context,queue);
        requestStage.getStage(id, new MaRequestStage.getStageCallBack() {
            @Override
            public void onSuccess(Stage unStage) {
                //Toast.makeText(context,"Réussite : "+unStage.toString(),Toast.LENGTH_LONG).show();

                TextView textEnseignant = findViewById(R.id.textView5);
               textEnseignant.setText(String.valueOf(unStage.getEnseignant().getPrenom()+" " +unStage.getEnseignant().getNom()));

               TextView textEntreprise = findViewById(R.id.textView7);
               textEntreprise.setText(String.valueOf(unStage.getEntreprise().getNom()));

               TextView textTuteur = findViewById(R.id.textView9);
               textTuteur.setText(String.valueOf(unStage.getTuteur().getNom()));

               EditText editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
               editTextTextPersonName.setText(unStage.getIntitule());

               EditText dateDebut = findViewById(R.id.dateDebut);
                dateDebut.setText(unStage.getDateDebut());

                EditText datefin = findViewById(R.id.dateFin);
                datefin.setText(unStage.getDateFin());

            }

            @Override
            public void onError(String message) {
                Log.d("message d'erreur",message);
            }
        });


        /* FIN DE BDD */



        TextView textEleve = findViewById(R.id.textView3);
        textEleve.setText(etudiant);
        final String eleve = textEleve.getText().toString();

        final TextView textEnseignant = findViewById(R.id.textView5);

        final TextView textEntreprise = findViewById(R.id.textView7);

        final TextView textTuteur = findViewById(R.id.textView9);

        final EditText intituleStage = findViewById(R.id.editTextTextPersonName);
        final EditText condition = findViewById(R.id.edit_text);
        final EditText bilan = findViewById(R.id.edit_text2);
        final EditText ressource = findViewById(R.id.edit_text3);
        final EditText commentaire = findViewById(R.id.edit_text4);

        final EditText edittextDebut = findViewById(R.id.dateDebut);
        final EditText edittextFin = findViewById(R.id.dateFin);
        final EditText edittextVisite = findViewById(R.id.dateVisite);


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
                                json.put("ELEVE", eleve);
                                json.put("ENSEIGNANT", textEnseignant.getText().toString());
                                json.put("ENTREPRISE", textEntreprise.getText().toString());
                                json.put("TUTEUR", textTuteur.getText().toString());
                                json.put("INTITULE STAGE", intituleStage.getText().toString());
                                json.put("Date debut", edittextDebut.getText().toString());
                                json.put("Date fin", edittextFin.getText().toString());
                                json.put("DATE Visite", edittextVisite.getText().toString());
                                json.put("CONDITION", condition.getText().toString());
                                json.put("BILAN", bilan.getText().toString());
                                json.put("COMMENTAIRE", commentaire.getText().toString());
                                json.put("tuteur oral", jury[0]);
                                json.put("prendre un stagiaire ?", checkbox[0] + checkbox[1]);


                            }catch (JSONException e) {
                                Log.d("test",e.toString());
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

//programmatin des checkbox
        CheckBox chk = (CheckBox) findViewById(R.id.checkBox);
        CheckBox chk2 = (CheckBox) findViewById(R.id.checkBox2);
        checkbox[0] = "";
        chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                // Check which checkbox was clicked
                if (checked){
                    checkbox[0] += " 1ère année";
                }
                else{
                    checkbox[0] = "";
                }

            }
        });
        chk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                // Check which checkbox was clicked
                if (checked){
                    checkbox[1] += " 2ème année";
                }
                else{
                    checkbox[1] = "";
                }

            }
        });

        //programmation des boutons radios
        RadioGroup radioGroupStage = findViewById(R.id.radioGroupStage);
        radioGroupStage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged (RadioGroup radioGroupStage,int i){
                switch (i) {
                    case R.id.radioButton:
                        jury[0] ="OUI";
                        break;
                    case R.id.radioButton2:
                        jury[0] ="NON";
                        break;

                }
            }

        });

        final Calendar myCalendarDebut = Calendar.getInstance();
        final Calendar myCalendarFin = Calendar.getInstance();
        final Calendar myCalendarVisite = Calendar.getInstance();

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
