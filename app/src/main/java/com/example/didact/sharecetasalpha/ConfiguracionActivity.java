package com.example.didact.sharecetasalpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ConfiguracionActivity extends AppCompatActivity {

    Spinner spComidaFavConf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        spComidaFavConf = (Spinner)findViewById(R.id.spComidaFavConf);
        String[] tipos={"Ensaladas","Pizza","Sopas","Postres","Africana","Americana","Asiatica","Caribeña","India",
                        "Italiana","Mexicana"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,tipos);
        spComidaFavConf.setAdapter(adaptador);
    }

    public void clickRecetasConf(View view){

        Intent mainIntent = new Intent().setClass(getApplicationContext(), MisRecetasActivity.class);
        startActivity(mainIntent);
        finish();
    }
    public void clickBuscadorConf(View view){

        Intent mainIntent = new Intent().setClass(getApplicationContext(), BuscadorActivity.class);
        startActivity(mainIntent);
        finish();

    }
    public void clickFavoritosConf(View view){

        Intent mainIntent = new Intent().setClass(getApplicationContext(), FavoritoActivity.class);
        startActivity(mainIntent);
        finish();

    }
    public void clickConfiguracionConf(View view){


        Toast.makeText(getApplicationContext(),"Subir arriba",Toast.LENGTH_LONG).show();

    }

    public void guardarCambios (View view){

        Toast.makeText(getApplicationContext(),"Los cambios se han guardado",Toast.LENGTH_LONG).show();

    }

}
