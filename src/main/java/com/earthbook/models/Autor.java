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
    
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Column(name = "BIOGRAFIA")
    private String biografia;
    
    @Column(name = "IMAGEN")
    private String urlImagen;

    public Autor() {
    }
    
	public Autor(int id, String nombre, String biografia, String urlImagen) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.biografia = biografia;
		this.urlImagen = urlImagen;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}
}
