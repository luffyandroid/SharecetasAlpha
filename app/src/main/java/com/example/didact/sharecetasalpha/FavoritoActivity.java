package com.example.didact.sharecetasalpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FavoritoActivity extends AppCompatActivity {
    static final String EXTRA_RECETA="RECETA";

    ListView lvFavorito;
    ArrayList<CReceta> listaFavorito = new ArrayList<CReceta>();
    TextView tvUsuarioFav;

    DatabaseReference dbRef;
    ValueEventListener valueEventListener;

    CUsuario usu=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorito);

        pruebafavoritos();

        lvFavorito = (ListView)findViewById(R.id.lvRecetas);
        tvUsuarioFav=(TextView)findViewById(R.id.tvUsuarioFav);

        Bundle b= getIntent().getExtras();

        if (b!=null){

            usu = b.getParcelable(LoginActivity.EXTRA_USUARIO);
            tvUsuarioFav.setText(usu.getNombre());
        }


        lvFavorito = (ListView)findViewById(R.id.lvFavorito);

        AdaptadorReceta adaptadorReceta = new AdaptadorReceta(this,listaFavorito);
        lvFavorito.setAdapter(adaptadorReceta);
    }




    public void clickRecetasFav(View view){

        Intent mainIntent = new Intent().setClass(getApplicationContext(), MisRecetasActivity.class);

        startActivity(mainIntent);

    }
    public void clickBuscadorFav(View view){

        Intent mainIntent = new Intent().setClass(getApplicationContext(), BuscadorActivity.class);
        startActivity(mainIntent);


    }
    public void clickFavoritosFav(View view){


        Toast.makeText(getApplicationContext(),"Subir arriba",Toast.LENGTH_LONG).show();

    }
    public void clickConfiguracionFav(View view){

        Intent mainIntent = new Intent().setClass(getApplicationContext(), ConfiguracionActivity.class);
        startActivity(mainIntent);


    }

    private void pruebafavoritos(){
        List<String> lista_favoritos = usu.getFav();

        for (int i=0; i<lista_favoritos.size(); i++){
            String idReceta = lista_favoritos.get(i);

            dbRef = FirebaseDatabase.getInstance().getReference().child("receta/" + idReceta);

            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    cargarListView(dataSnapshot);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("LoginActivity", "DATABASE ERROR");
                }
            };
            dbRef.addValueEventListener(valueEventListener);

        }
    }



    private void cargarListView (DataSnapshot dataSnapshot){

        CReceta r =dataSnapshot.getValue(CReceta.class);


        listaFavorito.add(dataSnapshot.getValue(CReceta.class));

        AdaptadorReceta adaptadorReceta=new AdaptadorReceta(this,listaFavorito);
        lvFavorito.setAdapter(adaptadorReceta);

        lvFavorito.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CReceta c =((CReceta)parent.getItemAtPosition(position));
                String nombre = c.getNombre();
                String usuario=c.getUsuario();
                String preparacion=c.getPreparacion();
                String foto=c.getFoto();
                CReceta recetaenviada=new CReceta(nombre, usuario, preparacion, foto);
                Intent i = new Intent(getApplicationContext(), RecetaAbiertaActivity.class);
                i.putExtra(EXTRA_RECETA, recetaenviada);
                startActivity(i);

            }
        });

    }

}
