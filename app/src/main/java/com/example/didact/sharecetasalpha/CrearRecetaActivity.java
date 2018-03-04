package com.example.didact.sharecetasalpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CrearRecetaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_receta);
    }
    public void finalizarReceta(View view){

        Intent mainIntent = new Intent().setClass(getApplicationContext(), MisRecetasActivity.class);
        startActivity(mainIntent);
        finish();

    }

}
