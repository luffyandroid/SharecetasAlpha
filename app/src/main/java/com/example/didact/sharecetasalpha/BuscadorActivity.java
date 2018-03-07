package com.example.didact.sharecetasalpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class BuscadorActivity extends AppCompatActivity {

    ListView lvBuscador;
    ArrayList<CReceta> listaBuscador = new ArrayList<CReceta>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador);

        cargarDatos();

        lvBuscador = (ListView)findViewById(R.id.lvBuscador);

        AdaptadorReceta adaptadorReceta = new AdaptadorReceta(this,listaBuscador);
        lvBuscador.setAdapter(adaptadorReceta);

    }

    private void cargarDatos(){
        listaBuscador.add(new CReceta("Sandwich","Pepe123","pan, york y queso","foto de sandwich"));
        listaBuscador.add(new CReceta("Espaguetis","Jimena5823","oregano, pasta y tomate","foto de espaguetis"));
        listaBuscador.add(new CReceta("Tortilla","Luiti23","huevo y papas","foto de tortilla"));
    }

    public void buscarReceta(View view){

        Toast.makeText(getApplicationContext(),"Busca receta",Toast.LENGTH_LONG).show();

    }

    public void clickRecetasBus(View view){

        Intent mainIntent = new Intent().setClass(getApplicationContext(), MisRecetasActivity.class);
        startActivity(mainIntent);

    }
    public void clickBuscadorBus(View view){

        Toast.makeText(getApplicationContext(),"Subir arriba",Toast.LENGTH_LONG).show();

    }
    public void clickFavoritosBus(View view){

        Intent mainIntent = new Intent().setClass(getApplicationContext(), FavoritoActivity.class);
        startActivity(mainIntent);


    }
    public void clickConfiguracionBus(View view){

        Intent mainIntent = new Intent().setClass(getApplicationContext(), ConfiguracionActivity.class);
        startActivity(mainIntent);


    }
}
