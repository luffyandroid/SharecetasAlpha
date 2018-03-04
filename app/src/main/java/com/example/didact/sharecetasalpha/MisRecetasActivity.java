package com.example.didact.sharecetasalpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MisRecetasActivity extends AppCompatActivity {

    ListView lvRecetas;
    ArrayList<CReceta> listaRecetas = new ArrayList<CReceta>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_recetas);

        cargarDatos();

        lvRecetas = (ListView)findViewById(R.id.lvRecetas);

        AdaptadorReceta adaptadorReceta = new AdaptadorReceta(this,listaRecetas);
        lvRecetas.setAdapter(adaptadorReceta);

    }


    private void cargarDatos(){
        listaRecetas.add(new CReceta("Sandwich","Pepe123","pan, york y queso","foto de sandwich"));
        listaRecetas.add(new CReceta("Espaguetis","Jimena5823","oregano, pasta y tomate","foto de espaguetis"));
        listaRecetas.add(new CReceta("Tortilla","Luiti23","huevo y papas","foto de tortilla"));
    }

    public void nuevaReceta(View view){

        Intent mainIntent = new Intent().setClass(getApplicationContext(), CrearRecetaActivity.class);
        startActivity(mainIntent);
        finish();

    }

    public void clickRecetasRes(View view){

        Toast.makeText(getApplicationContext(),"Subir arriba",Toast.LENGTH_LONG).show();
    }
    public void clickBuscadorRes(View view){

        Intent mainIntent = new Intent().setClass(getApplicationContext(), BuscadorActivity.class);
        startActivity(mainIntent);
        finish();

    }
    public void clickFavoritosRes(View view){

        Intent mainIntent = new Intent().setClass(getApplicationContext(), FavoritoActivity.class);
        startActivity(mainIntent);
        finish();

    }
    public void clickConfiguracionRes(View view){

        Intent mainIntent = new Intent().setClass(getApplicationContext(), ConfiguracionActivity.class);
        startActivity(mainIntent);
        finish();

    }

}
