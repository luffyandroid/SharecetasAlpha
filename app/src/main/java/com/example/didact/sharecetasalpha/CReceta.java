package com.example.didact.sharecetasalpha;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Luffyandroid on 01/03/2018.
 */

public class CReceta implements Parcelable{
    String nombre;
    String usuario;
    String preparacion;
    String foto;

    public static final Parcelable.Creator<CReceta> CREATOR = new
            Parcelable.Creator<CReceta>(){
                public CReceta createFromParcel(Parcel in){
                    return new CReceta(in);
                }

                public CReceta[] newArray(int size){
                    return new CReceta[size];
                }
            };


    public CReceta(String nombre, String usuario, String preparacion, String foto) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.preparacion = preparacion;
        this.foto = foto;
    }

    public CReceta(Parcel p){
        readFromParcel(p);

    }

    public CReceta(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeString(this.usuario);
        dest.writeString(this.preparacion);
        dest.writeString(this.foto);
    }

    private void  readFromParcel(Parcel p){
        this.nombre = p.readString();
        this.usuario = p.readString();
        this.preparacion = p.readString();
        this.foto = p.readString();
    }

}
