package com.earthbook.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AUTORES")
public class Autor {

    @Id
    @Column(name = "ID_AU")
    private int id;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Column(name = "IMAGEN")
    private String urlImagen;

    public Autor() {
    }
    
    public Autor(int id, String descripcion, String urlImagen) {
        this.id = id;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
 
}
