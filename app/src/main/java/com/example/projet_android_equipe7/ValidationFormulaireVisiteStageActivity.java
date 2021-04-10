package com.example.projet_android_equipe7;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ValidationFormulaireVisiteStageActivity extends Activity {

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation_formulaire_visite);


        String json = "";
        Intent intent = getIntent();
        if (intent != null) {
            json = intent.getStringExtra("EXTRA_JSON");
        }

        TextView text = findViewById(R.id.textView21);
        text.setText(json);


    }

}
