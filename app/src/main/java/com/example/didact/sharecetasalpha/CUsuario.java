package com.example.didact.sharecetasalpha;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Luffyandroid on 02/03/2018.
 */

public class CUsuario implements Parcelable{
    String email;
    String nombre;
    String contrasena;
    String[] fav;
    String foto;
    String descripcion;
    String comidafav;

    public static final Parcelable.Creator<CUsuario> CREATOR = new
            Parcelable.Creator<CUsuario>() {
                public CUsuario createFromParcel(Parcel in) {
                    return new CUsuario(in);
                }
                public CUsuario[] newArray(int size) {
                    return new CUsuario[size];
                }
            };

    public CUsuario(String email, String usuario, String contrasena, String[] fav, String foto, String descripcion, String comidafav) {
        this.email = email;
        this.nombre = usuario;
        this.contrasena = contrasena;
        this.fav = fav;
        this.foto = foto;
        this.descripcion = descripcion;
        this.comidafav = comidafav;
    }

    public CUsuario(Parcel p){
        readFromParcel(p);
    }

    public CUsuario(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String[] getFav() {
        return fav;
    }

    public void setFav(String[] contrasena2) {
        this.fav = fav;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getComidafav() {
        return comidafav;
    }

    public void setComidafav(String comidafav) {
        this.comidafav = comidafav;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.email);
        dest.writeString(this.nombre);
        dest.writeString(this.contrasena);
        dest.writeStringArray(this.fav);
        dest.writeString(this.foto);
        dest.writeString(this.descripcion);
        dest.writeString(this.comidafav);
    }

    private void readFromParcel(Parcel p){
        this.email = p.readString();
        this.nombre = p.readString();
        this.contrasena = p.readString();
        p.readStringArray(this.fav);
        this.foto = p.readString();
        this.descripcion = p.readString();
        this.comidafav = p.readString();
    }
}
