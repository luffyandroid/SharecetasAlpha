package com.example.didact.sharecetasalpha;

/**
 * Created by Luffyandroid on 02/03/2018.
 */
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class AdaptadorReceta extends ArrayAdapter<CReceta> {
    ArrayList<CReceta> recetas;
    Context c;

    StorageReference storageRf;
    public AdaptadorReceta(Context c, ArrayList<CReceta> recetas) {
        super(c, R.layout.item_receta, recetas);
        this.recetas = recetas;
        this.c = c;
        this.storageRf = FirebaseStorage.getInstance().getReference();
    }
    public View getView(int position, View view, ViewGroup
            viewGroup) {
        LayoutInflater inflater =
                LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.item_receta, null);

        TextView tvNombre = (TextView)
                item.findViewById(R.id.tvNombreItem);
        tvNombre.setText(recetas.get(position).getNombre() );

        TextView tvPreparacion = (TextView)
                item.findViewById(R.id.tvPreparacionItem);
        tvPreparacion.setText(recetas.get(position).getPreparacion() );

        String imagen = recetas.get(position).getFoto();
        /*int idImagen = c.getResources().getIdentifier(imagen,"drawable", c.getPackageName());*/
        ImageView iv_logo = (ImageView) item.findViewById(R.id.imgFotoItem);
        /*iv_logo.setImageResource(idImagen);*/

        cargarImagen(imagen, item, iv_logo);

        return item;
    }

    private void cargarImagen(String nombre, final View item, final ImageView iv_logo){

        storageRf.child("imagenes/recetas/"+nombre).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(item).load(uri.toString()).into(iv_logo);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }

}
