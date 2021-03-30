package com.example.projet_android_equipe7;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FormulaireVisiteStageActivity extends Activity {

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire_visite);

        String etudiant = "";
        //on va récupérer les trois valeurs provenant de NewReleveActivity
        Intent intent = getIntent();
        if (intent != null) {
            etudiant = intent.getStringExtra("EXTRA_ETUDIANT");

            TextView textEtudiant = findViewById(R.id.textView3);
            textEtudiant.setText(etudiant);


        }


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
