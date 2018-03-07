package com.example.didact.sharecetasalpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RegistroActivity extends AppCompatActivity {

    EditText etUsuarioReg, etMailReg, etContrasenaReg, etContrasena2Reg;
    String foto="", descripcion, comidaFav;
    CheckBox cbTerminos;
    TextView tvFalloRegistro;
    DatabaseReference dbRef;
    List<String> fav =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etUsuarioReg=(EditText)findViewById(R.id.etUsuarioReg);
        etMailReg=(EditText)findViewById(R.id.etMailReg);
        etContrasenaReg=(EditText)findViewById(R.id.etContrasenaReg);
        etContrasena2Reg=(EditText)findViewById(R.id.etContrasena2Reg);
        cbTerminos=(CheckBox)findViewById(R.id.cbTerminos);
        tvFalloRegistro=(TextView)findViewById(R.id.tvFalloRegistro);

        descripcion="";
        comidaFav="";

    }

    public void fotoRegistro(View view){
        Toast.makeText(getApplicationContext(),"Foto tomada",Toast.LENGTH_LONG).show();
        foto = "foto";

    }

    public void clickFinalizar(View view){

        String nombre = etUsuarioReg.getText().toString();
        String email = etMailReg.getText().toString();
        String contrasena = etContrasenaReg.getText().toString();
        String contrasena2 = etContrasena2Reg.getText().toString();

        if (nombre.equals("") || email.equals("") || contrasena.equals("") || contrasena2.equals("") || foto.equals("")){

            tvFalloRegistro.setText("Debes rellenar todos los campos");

        }else{
            if (!contrasena.equals(contrasena2)){
                tvFalloRegistro.setText("Las contraseñas deben ser iguales");
            }else{
                if (cbTerminos.isChecked()){

                    CUsuario nuevoUsuario=new CUsuario(email, nombre, contrasena, fav, foto, descripcion, comidaFav);
                    dbRef = FirebaseDatabase.getInstance().getReference().child("usuario");
                    dbRef.child(nombre).setValue(nuevoUsuario, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError == null){

                                Toast.makeText(getApplicationContext(),"Insertado correctamente",Toast.LENGTH_LONG).show();


                            }else{

                                Toast.makeText(getApplicationContext(),"No se puede insertar el usuario",Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                    Intent mainIntent = new Intent().setClass(getApplicationContext(), LoginActivity.class);
                    startActivity(mainIntent);
                    finish();



                }else{
                    tvFalloRegistro.setText("Debes aceptar los términos de uso");
                }
            }
        }


    }

}
