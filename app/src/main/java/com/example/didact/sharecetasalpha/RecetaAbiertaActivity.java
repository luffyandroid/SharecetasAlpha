package com.example.didact.sharecetasalpha;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RecetaAbiertaActivity extends AppCompatActivity {

    static final String EXTRA_USUARIO="USUARIO";

    CUsuario usu=null;

    DatabaseReference dbRef;
    ValueEventListener valueEventListener;
    StorageReference storageRf;

    TextView tvNombreRecetaAbierta, tvUsuarioRecetaAbierta, tvDescripcionRecetaAbierta, tvNombreUsuarioRecetaAbierta;
    ImageView imgFotoReceta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receta_abierta);

        tvNombreRecetaAbierta=(TextView)findViewById(R.id.tvNombreRecetaAbierta);
        tvUsuarioRecetaAbierta=(TextView)findViewById(R.id.tvUsuarioRecetaAbierta);
        tvDescripcionRecetaAbierta=(TextView)findViewById(R.id.tvDescripcionRecetaAbierta);
        tvNombreUsuarioRecetaAbierta=(TextView)findViewById(R.id.tvNombreUsuarioRecetaAbierta);
        imgFotoReceta=(ImageView)findViewById(R.id.imgRecetaAbierta);
        storageRf = FirebaseStorage.getInstance().getReference();

        Bundle b= getIntent().getExtras();

        if (b!=null){

            usu = b.getParcelable(LoginActivity.EXTRA_USUARIO);
            tvNombreUsuarioRecetaAbierta.setText(usu.getNombre());
            CReceta c = b.getParcelable(MisRecetasActivity.EXTRA_RECETA);
            tvNombreRecetaAbierta.setText(c.getNombre());
            tvUsuarioRecetaAbierta.setText(c.getUsuario());
            tvDescripcionRecetaAbierta.setText(c.getPreparacion());
            String foto= c.getFoto();

            cargarImagen(foto);



        }

    }

    public void clickRecetasResAbi(View view){
        datosUsuario();
        Intent mainIntent = new Intent().setClass(getApplicationContext(), MisRecetasActivity.class);
        mainIntent.putExtra(EXTRA_USUARIO, usu);
        startActivity(mainIntent);
        finish();
    }
    public void clickBuscadorResAbi(View view){
        datosUsuario();
        Intent mainIntent = new Intent().setClass(getApplicationContext(), BuscadorActivity.class);
        mainIntent.putExtra(EXTRA_USUARIO, usu);
        startActivity(mainIntent);
        finish();

    }
    public void clickFavoritosResAbi(View view){
        datosUsuario();
        Intent mainIntent = new Intent().setClass(getApplicationContext(), FavoritoActivity.class);
        mainIntent.putExtra(EXTRA_USUARIO, usu);
        startActivity(mainIntent);
        finish();

    }
    public void clickConfiguracionResAbi(View view){
        datosUsuario();
        Intent mainIntent = new Intent().setClass(getApplicationContext(), ConfiguracionActivity.class);
        mainIntent.putExtra(EXTRA_USUARIO, usu);
        startActivity(mainIntent);
        finish();

    }

    public void datosUsuario() {

        String usuario = tvNombreUsuarioRecetaAbierta.getText().toString();


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
    private void cargarImagen(String foto){

        storageRf.child("imagenes/recetas/"+foto).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext()).load(uri.toString()).into(imgFotoReceta);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }


}
