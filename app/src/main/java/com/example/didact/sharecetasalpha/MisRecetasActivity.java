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

public class MisRecetasActivity extends AppCompatActivity {
    static final String EXTRA_RECETA="RECETA";
    static final String EXTRA_USUARIO="USUARIO";


    ListView lvRecetas;
    TextView tvUsuarioMisRecetas;
    ArrayList<CReceta> listaRecetas = new ArrayList<CReceta>();

    DatabaseReference dbRef;
    ValueEventListener valueEventListener;

    CUsuario usu=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_recetas);

        cargarDatosFirebase();

        lvRecetas = (ListView)findViewById(R.id.lvRecetas);
        tvUsuarioMisRecetas=(TextView)findViewById(R.id.tvUsuarioMisRecetas);

        Bundle b= getIntent().getExtras();

        if (b!=null){

              usu = b.getParcelable(LoginActivity.EXTRA_USUARIO);
            tvUsuarioMisRecetas.setText(usu.getNombre());
        }




    }




    public void nuevaReceta(View view){

        Intent mainIntent = new Intent().setClass(getApplicationContext(), CrearRecetaActivity.class);
        startActivity(mainIntent);


    }

    public void clickRecetasRes(View view){

        Toast.makeText(getApplicationContext(),"Subir arriba",Toast.LENGTH_LONG).show();
    }
    public void clickBuscadorRes(View view){
        datosUsuario();
        Intent mainIntent = new Intent().setClass(getApplicationContext(), BuscadorActivity.class);
        mainIntent.putExtra(EXTRA_USUARIO, usu);
        startActivity(mainIntent);


    }
    public void clickFavoritosRes(View view){
        datosUsuario();
        Intent mainIntent = new Intent().setClass(getApplicationContext(), FavoritoActivity.class);
        mainIntent.putExtra(EXTRA_USUARIO, usu);
        startActivity(mainIntent);


    }
    public void clickConfiguracionRes(View view){
        datosUsuario();
        Intent mainIntent = new Intent().setClass(getApplicationContext(), ConfiguracionActivity.class);
        mainIntent.putExtra(EXTRA_USUARIO, usu);
        startActivity(mainIntent);


    }




    private void cargarListView (DataSnapshot dataSnapshot){

        CReceta r =dataSnapshot.getValue(CReceta.class);

        if (r.getUsuario().equals(usu.getNombre())) {
            listaRecetas.add(dataSnapshot.getValue(CReceta.class));

            AdaptadorReceta adaptadorReceta=new AdaptadorReceta(this,listaRecetas);
            lvRecetas.setAdapter(adaptadorReceta);

            lvRecetas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                    datosUsuario();
                    i.putExtra(EXTRA_USUARIO, usu);
                    startActivity(i);
                }
            });
        }

    }

    private void cargarDatosFirebase(){


            dbRef = FirebaseDatabase.getInstance().getReference().child("receta");


            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    listaRecetas.clear();
                    for (DataSnapshot recetaDataSnapshot : dataSnapshot.getChildren()) {
                        cargarListView(recetaDataSnapshot);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("MisRecetasActivity", "DATABASE ERROR");
                }
            };

            dbRef.addValueEventListener(valueEventListener);

    }


    //-----------------------------------------------------------------


    public void datosUsuario() {

        String usuario = tvUsuarioMisRecetas.getText().toString();


        dbRef = FirebaseDatabase.getInstance().getReference().child("usuario/" + usuario);

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    usu = dataSnapshot.getValue(CUsuario.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("LoginActivity", "DATABASE ERROR");
            }
        };
        dbRef.addValueEventListener(valueEventListener);


    }

}
