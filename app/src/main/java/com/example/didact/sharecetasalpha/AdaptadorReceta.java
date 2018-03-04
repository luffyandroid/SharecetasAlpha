package com.example.didact.sharecetasalpha;

/**
 * Created by Luffyandroid on 02/03/2018.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class AdaptadorReceta extends ArrayAdapter<CReceta> {
    ArrayList<CReceta> recetas;
    Context c;
    public AdaptadorReceta(Context c, ArrayList<CReceta> coches) {
        super(c, R.layout.item_receta, coches);
        this.recetas = recetas;
        this.c = c;
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
        int idImagen = c.getResources().getIdentifier(imagen,"drawable", c.getPackageName());
        ImageView iv_logo = (ImageView) item.findViewById(R.id.imgFotoItem);
        iv_logo.setImageResource(idImagen);
        return item;
    }
}
