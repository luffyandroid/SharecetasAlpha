package com.example.didact.sharecetasalpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FavoritoActivity extends AppCompatActivity {

    ListView lvFavorito;
    ArrayList<CReceta> listaFavorito = new ArrayList<CReceta>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorito);

        cargarDatos();

        lvFavorito = (ListView)findViewById(R.id.lvFavorito);

        AdaptadorReceta adaptadorReceta = new AdaptadorReceta(this,listaFavorito);
        lvFavorito.setAdapter(adaptadorReceta);
    }

    private void cargarDatos(){
        listaFavorito.add(new CReceta("Sandwich","Pepe123","pan, york y queso","foto de sandwich"));
        listaFavorito.add(new CReceta("Espaguetis","Jimena5823","oregano, pasta y tomate","foto de espaguetis"));
        listaFavorito.add(new CReceta("Tortilla","Luiti23","huevo y papas","foto de tortilla"));
    }


    public void clickRecetasFav(View view){

        Intent mainIntent = new Intent().setClass(getApplicationContext(), MisRecetasActivity.class);
        startActivity(mainIntent);
        finish();
    }
    public void clickBuscadorFav(View view){

        Intent mainIntent = new Intent().setClass(getApplicationContext(), BuscadorActivity.class);
        startActivity(mainIntent);
        finish();

    }
    public void clickFavoritosFav(View view){


        Toast.makeText(getApplicationContext(),"Subir arriba",Toast.LENGTH_LONG).show();

    }
    public void clickConfiguracionFav(View view){

        Intent mainIntent = new Intent().setClass(getApplicationContext(), ConfiguracionActivity.class);
        startActivity(mainIntent);
        finish();

    }

}
