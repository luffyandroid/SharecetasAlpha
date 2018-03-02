package com.example.didact.sharecetasalpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }
    public void clickRegistrar(View view) {
        Intent mainIntent = new Intent().setClass(getApplicationContext(), RegistroActivity.class);
        startActivity(mainIntent);
        finish();
    }

    public void clickLogin(View view) {
        Intent mainIntent = new Intent().setClass(getApplicationContext(), LoginActivity.class);
        startActivity(mainIntent);
        finish();
    }

}
