package com.example.didact.sharecetasalpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    EditText etUsuarioReg, etMailReg, etContrasenaReg, etContrasena2Reg;
    String foto;
    CheckBox cbTerminos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etUsuarioReg=(EditText)findViewById(R.id.etUsuarioReg);
        etMailReg=(EditText)findViewById(R.id.etMailReg);
        etContrasenaReg=(EditText)findViewById(R.id.etContrasenaReg);
        etContrasena2Reg=(EditText)findViewById(R.id.etContrasena2Reg);
        cbTerminos=(CheckBox)findViewById(R.id.cbTerminos);

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

        Intent mainIntent = new Intent().setClass(getApplicationContext(), LoginActivity.class);
        startActivity(mainIntent);
        finish();
    }

}
