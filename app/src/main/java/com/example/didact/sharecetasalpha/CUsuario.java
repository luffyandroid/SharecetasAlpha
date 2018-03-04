package com.example.didact.sharecetasalpha;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Luffyandroid on 02/03/2018.
 */

public class CUsuario implements Parcelable{
    String email;
    String usuario;
    String contrasena;
    String contrasena2;
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

    public CUsuario(String email, String usuario, String contrasena, String contrasena2, String foto, String descripcion, String comidafav) {
        this.email = email;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.contrasena2 = contrasena2;
        this.foto = foto;
        this.descripcion = descripcion;
        this.comidafav = comidafav;
    }

    public CUsuario(Parcel p){
        readFromParcel(p);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String nombre) {
        this.usuario = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getContrasena2() {
        return contrasena2;
    }

    public void setContrasena2(String contrasena2) {
        this.contrasena2 = contrasena2;
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
        dest.writeString(this.usuario);
        dest.writeString(this.contrasena);
        dest.writeString(this.contrasena2);
        dest.writeString(this.foto);
        dest.writeString(this.descripcion);
        dest.writeString(this.comidafav);
    }

    private void readFromParcel(Parcel p){
        this.email = p.readString();
        this.usuario = p.readString();
        this.contrasena = p.readString();
        this.contrasena2 = p.readString();
        this.foto = p.readString();
        this.descripcion = p.readString();
        this.comidafav = p.readString();
    }
}
