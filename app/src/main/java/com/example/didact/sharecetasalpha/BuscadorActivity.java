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

public class BuscadorActivity extends AppCompatActivity {
    static final String EXTRA_RECETA="RECETA";
    static final String EXTRA_USUARIO="USUARIO";

    TextView tvUsuarioBuscador;
    ListView lvBuscador;
    ArrayList<CReceta> listaBuscador = new ArrayList<CReceta>();

    DatabaseReference dbRef;
    ValueEventListener valueEventListener;

    CUsuario usu=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador);

        cargarDatosFirebase();

        tvUsuarioBuscador=(TextView)findViewById(R.id.tvUsuarioBuscador);

        Bundle b= getIntent().getExtras();

        if (b!=null){

            usu = b.getParcelable(LoginActivity.EXTRA_USUARIO);
            tvUsuarioBuscador.setText(usu.getNombre());
        }


        lvBuscador = (ListView)findViewById(R.id.lvBuscador);


    }



    public void buscarReceta(View view){

        Toast.makeText(getApplicationContext(),"Busca receta",Toast.LENGTH_LONG).show();

    }

    public void clickRecetasBus(View view){

        datosUsuario();
        Intent mainIntent = new Intent().setClass(getApplicationContext(), MisRecetasActivity.class);
        mainIntent.putExtra(EXTRA_USUARIO, usu);
        startActivity(mainIntent);

    }
    public void clickBuscadorBus(View view){

        Toast.makeText(getApplicationContext(),"Subir arriba",Toast.LENGTH_LONG).show();

    }
    public void clickFavoritosBus(View view){
        datosUsuario();
        Intent mainIntent = new Intent().setClass(getApplicationContext(), FavoritoActivity.class);
        mainIntent.putExtra(EXTRA_USUARIO, usu);
        startActivity(mainIntent);


    }
    public void clickConfiguracionBus(View view){
        datosUsuario();
        Intent mainIntent = new Intent().setClass(getApplicationContext(), ConfiguracionActivity.class);
        mainIntent.putExtra(EXTRA_USUARIO, usu);
        startActivity(mainIntent);


    }

    private void cargarListView (DataSnapshot dataSnapshot){
        listaBuscador.add(dataSnapshot.getValue(CReceta.class));

        AdaptadorReceta adaptadorReceta=new AdaptadorReceta(this,listaBuscador);
        lvBuscador.setAdapter(adaptadorReceta);

        lvBuscador.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    private void cargarDatosFirebase(){

        dbRef= FirebaseDatabase.getInstance().getReference().child("receta");

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaBuscador.clear();
                for (DataSnapshot recetaDataSnapshot: dataSnapshot.getChildren()){
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

    public void datosUsuario() {

        String usuario = tvUsuarioBuscador.getText().toString();


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
