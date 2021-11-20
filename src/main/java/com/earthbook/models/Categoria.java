package com.earthbook.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIAS")
public class Categoria {

	@Id
	@Column(name = "ID_CAT")
	private int id;


    @Column(name = "DESCRIPCION")
    private String nombrecat;

	@Column(name = "IMAGEN")
	private String urlImagen;

    
    
	public Categoria() {
		super();
	}

	public Categoria(int id, String nombrecat, String urlImagen) {
		super();
		this.id = id;
		this.nombrecat = nombrecat;
		this.urlImagen = urlImagen;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombrecat() {
		return nombrecat;
	}

	public void setNombrecat(String nombrecat) {
		this.nombrecat = nombrecat;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

   
}
