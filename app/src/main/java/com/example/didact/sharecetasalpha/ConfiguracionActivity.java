package com.example.didact.sharecetasalpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ConfiguracionActivity extends AppCompatActivity {

    static final String EXTRA_USUARIO="USUARIO";

    Spinner spComidaFavConf;
    TextView tvUsuarioConf;
    EditText etUsuarioConf, etContrasenaConf, etContrasenaNuevaConf, etContrasenaNueva2Conf, etDescripcionConf, etMailConf;

    DatabaseReference dbRef;
    ValueEventListener valueEventListener;

    String condFoto="no";
    String fotoTomada="no";

    CUsuario usu=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        spComidaFavConf = (Spinner)findViewById(R.id.spComidaFavConf);
        String[] tipos={"No se por cual decidirme ( *_*)U","Ensaladas","Pizza","Sopas","Postres","Africana","Americana","Asiatica","Caribeña","India",
                        "Italiana","Mexicana"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,tipos);
        spComidaFavConf.setAdapter(adaptador);

        tvUsuarioConf=(TextView)findViewById(R.id.tvUsuarioConf);

        etUsuarioConf=(EditText)findViewById(R.id.etNombreConf);
        etContrasenaConf=(EditText)findViewById(R.id.etContrasenaConf);
        etContrasenaNuevaConf=(EditText)findViewById(R.id.etContrasenaNuevaConf);
        etContrasenaNueva2Conf=(EditText)findViewById(R.id.etContrasenaNueva2Conf);
        etDescripcionConf=(EditText)findViewById(R.id.etDescripcionConf);
        etMailConf=(EditText)findViewById(R.id.etMailConf);

        Bundle b= getIntent().getExtras();

        if (b!=null){

            usu = b.getParcelable(LoginActivity.EXTRA_USUARIO);
            tvUsuarioConf.setText(usu.getNombre());
            etUsuarioConf.setText(usu.getNombre());
            etMailConf.setText(usu.getEmail());
            etDescripcionConf.setText(usu.getDescripcion());
            spComidaFavConf.setSelection(0);

        }
    }

    public void clickRecetasConf(View view){
        datosUsuario();
        Intent mainIntent = new Intent().setClass(getApplicationContext(), MisRecetasActivity.class);
        mainIntent.putExtra(EXTRA_USUARIO, usu);
        startActivity(mainIntent);

    }
    public void clickBuscadorConf(View view){
        datosUsuario();
        Intent mainIntent = new Intent().setClass(getApplicationContext(), BuscadorActivity.class);
        mainIntent.putExtra(EXTRA_USUARIO, usu);
        startActivity(mainIntent);


    }
    public void clickFavoritosConf(View view){
        datosUsuario();
        Intent mainIntent = new Intent().setClass(getApplicationContext(), FavoritoActivity.class);
        mainIntent.putExtra(EXTRA_USUARIO, usu);
        startActivity(mainIntent);


    }
    public void clickConfiguracionConf(View view){


        Toast.makeText(getApplicationContext(),"Subir arriba",Toast.LENGTH_LONG).show();

    }

    public void guardarCambios (View view){

        Toast.makeText(getApplicationContext(),"Los cambios se han guardado",Toast.LENGTH_LONG).show();

    }

    public void datosUsuario() {

        String usuario = tvUsuarioConf.getText().toString();


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

    public void clickModificar(View view){

        String nombre = etUsuarioConf.getText().toString();
        String mail = etMailConf.getText().toString();
        String contrasena = etContrasenaConf.getText().toString();
        String contrasenaNueva = etContrasenaNuevaConf.getText().toString();
        String contrasenaNueva2 = etContrasenaNuevaConf.getText().toString();
        String indiceSeleccionado = spComidaFavConf.getSelectedItem().toString();
        String descripcion = etDescripcionConf.getText().toString();

        if (etUsuarioConf.equals("") || etMailConf.equals("") || etContrasenaConf.equals("")) {

            Toast.makeText(getApplicationContext(), "Para salir de aqui debes tener mínimo tu nombre de ususario, mail y contraseña", Toast.LENGTH_LONG).show();
        }else{
            if (!contrasenaNueva.equals(contrasenaNueva2)){

                Toast.makeText(getApplicationContext(),"Has repetido la contraseña mal",Toast.LENGTH_LONG).show();

            }else{
                //Comprobar si metes foto
                if (condFoto.equals("si")){

                    CUsuario nuevoUsuario=new CUsuario(mail, nombre, contrasena, usu.getFav(), fotoTomada, descripcion, indiceSeleccionado);
                    dbRef = FirebaseDatabase.getInstance().getReference().child("jugadores");

                    dbRef.child(indiceSeleccionado).setValue(nuevoUsuario, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError == null){

                                Toast.makeText(getApplicationContext(),"Modificado correctamente",Toast.LENGTH_LONG).show();

                            }else{

                                Toast.makeText(getApplicationContext(),"No se puede modificar el ususario",Toast.LENGTH_LONG).show();

                            }
                        }
                    });

                }else{

                    CUsuario nuevoUsuario=new CUsuario(mail, nombre, contrasena, usu.getFav(), usu.getFoto(), descripcion, indiceSeleccionado);
                    dbRef = FirebaseDatabase.getInstance().getReference().child("jugadores");

                    dbRef.child(indiceSeleccionado).setValue(nuevoUsuario, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError == null){

                                Toast.makeText(getApplicationContext(),"Modificado correctamente",Toast.LENGTH_LONG).show();

                            }else{

                                Toast.makeText(getApplicationContext(),"No se puede modificar el ususario",Toast.LENGTH_LONG).show();

                            }
                        }
                    });

                }
            }




        }/*fin primer else*/


    }

    public void cambiarFoto(View view){

        Toast.makeText(getApplicationContext(),"Foto tomada",Toast.LENGTH_LONG).show();
        condFoto="si";
        fotoTomada="foto";

    }

}
