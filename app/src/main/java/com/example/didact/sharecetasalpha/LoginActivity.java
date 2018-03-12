package com.example.didact.sharecetasalpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText etUsuarioLogin, etContrasenaLogin;
    TextView tvFalloLogin;
    DatabaseReference dbRef;
    ValueEventListener valueEventListener;
    static final String EXTRA_USUARIO = "USUARIO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsuarioLogin = (EditText) findViewById(R.id.etUsuarioLogin);
        etContrasenaLogin = (EditText) findViewById(R.id.etContrasenaLogin);
        tvFalloLogin = (TextView) findViewById(R.id.tvFalloRegistro);
    }

    /**/


    public void clickInicio(View view) {

        String usuario = etUsuarioLogin.getText().toString();
        final String contrasena = etContrasenaLogin.getText().toString();

        dbRef = FirebaseDatabase.getInstance().getReference().child("usuario/" + usuario);

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue() == null) {
                    // tvFalloLogin.setText("El usuario no existe");
                    Toast.makeText(getApplicationContext(), "Usuario incorrecto", Toast.LENGTH_SHORT).show();
                } else {


                    CUsuario usu = dataSnapshot.getValue(CUsuario.class);

                    String contrasenadb = usu.getContrasena();

                    if (!contrasena.equals(contrasenadb)) {
                        // tvFalloLogin.setText("La contraseña no coincide");
                        Toast.makeText(getApplicationContext(), "Password incorrecto", Toast.LENGTH_SHORT).show();
                    } else {

                        Intent mainIntent = new Intent().setClass(getApplicationContext(), MisRecetasActivity.class);
                        mainIntent.putExtra(EXTRA_USUARIO, usu);
                        startActivity(mainIntent);
                        finish();

                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("LoginActivity", "DATABASE ERROR");
            }
        };
        dbRef.addValueEventListener(valueEventListener);


    }

    public void clickInicionolog(View view){
        Intent i=new Intent(getApplicationContext(),MisRecetasActivity.class);
        startActivity(i);

    }

}
