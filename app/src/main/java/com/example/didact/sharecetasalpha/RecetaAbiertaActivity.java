package com.example.didact.sharecetasalpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class RecetaAbiertaActivity extends AppCompatActivity {

    TextView tvNombreRecetaAbierta, tvUsuarioRecetaAbierta, tvDescripcionRecetaAbierta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receta_abierta);

        tvNombreRecetaAbierta=(TextView)findViewById(R.id.tvNombreRecetaAbierta);
        tvUsuarioRecetaAbierta=(TextView)findViewById(R.id.tvUsuarioRecetaAbierta);
        tvDescripcionRecetaAbierta=(TextView)findViewById(R.id.tvDescripcionRecetaAbierta);

        Bundle b= getIntent().getExtras();

        if (b!=null){

            CReceta c = b.getParcelable(MisRecetasActivity.EXTRA_RECETA);
            tvNombreRecetaAbierta.setText(c.getNombre());
            tvUsuarioRecetaAbierta.setText(c.getUsuario());
            tvDescripcionRecetaAbierta.setText(c.getPreparacion());
        }

    }

    public void clickRecetasResAbi(View view){

        Intent mainIntent = new Intent().setClass(getApplicationContext(), MisRecetasActivity.class);
        startActivity(mainIntent);
        finish();
    }
    public void clickBuscadorResAbi(View view){

        Intent mainIntent = new Intent().setClass(getApplicationContext(), BuscadorActivity.class);
        startActivity(mainIntent);
        finish();

    }
    public void clickFavoritosResAbi(View view){

        Intent mainIntent = new Intent().setClass(getApplicationContext(), FavoritoActivity.class);
        startActivity(mainIntent);
        finish();

    }
    public void clickConfiguracionResAbi(View view){

        Intent mainIntent = new Intent().setClass(getApplicationContext(), ConfiguracionActivity.class);
        startActivity(mainIntent);
        finish();

    }

}
