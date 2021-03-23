package com.example.projet_android_equipe7;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;

public class FormulaireVisiteStageActivity extends Activity {

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire_visite);

            String etudiant="";
        //on va récupérer les trois valeurs provenant de NewReleveActivity
        Intent intent = getIntent();
        if (intent != null) {
            etudiant=intent.getStringExtra("EXTRA_ETUDIANT");

            TextView textEtudiant = findViewById(R.id.textView3);
            textEtudiant.setText(etudiant);


        }



    }

}
