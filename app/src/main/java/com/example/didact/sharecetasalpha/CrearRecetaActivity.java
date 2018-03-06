package com.example.didact.sharecetasalpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CrearRecetaActivity extends AppCompatActivity {

    EditText etNombre, etPreparacion;
    String foto="";
    TextView tvUsuario;
    DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_receta);

        etNombre = (EditText)findViewById(R.id.etNombre);
        etPreparacion = (EditText)findViewById(R.id.etPreparacion);
        tvUsuario= (TextView)findViewById(R.id.tvUsuarioCrearRecetas);


    }

    public void fotoCrearReceta (View view){

        Toast.makeText(getApplicationContext(),"foto tomada",Toast.LENGTH_LONG).show();
        foto="foto";

    }

    public void finalizarReceta(View view){

        String nombre = etNombre.getText().toString();
        String preparacion = etPreparacion.getText().toString();
        String usuario = tvUsuario.getText().toString();

        if (nombre.equals("") || preparacion.equals("") || foto.equals("")){
            Toast.makeText(getApplicationContext(), "Debes rellenar los campos",Toast.LENGTH_LONG).show();
        }else{
            CReceta nuevoEmpleado=new CReceta(nombre, usuario, preparacion, foto);
            dbRef = FirebaseDatabase.getInstance().getReference().child("receta");
            dbRef.child(nombre+obtenerFechaSistema()).setValue(nuevoEmpleado, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError == null){

                        Toast.makeText(getApplicationContext(),"Insertado correctamente",Toast.LENGTH_LONG).show();


                    }else{

                        Toast.makeText(getApplicationContext(),"No se puede insertar la receta",Toast.LENGTH_LONG).show();

                    }
                }
            });
            Intent mainIntent = new Intent().setClass(getApplicationContext(), MisRecetasActivity.class);
            startActivity(mainIntent);
            finish();
        }





    }

    private String obtenerFechaSistema(){

        Calendar c= Calendar.getInstance();
        Date fecha = c.getTime();
        String fecha_string =
                c.get(Calendar.YEAR)+""+(c.get(Calendar.MONTH)+1)+""+c.get(Calendar.DAY_OF_MONTH);
        return fecha_string;
    }

}
