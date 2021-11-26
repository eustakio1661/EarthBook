package com.earthbook.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIAS")
public class Categoria {

	@Id
	@Column(name = "ID_CAT")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;


    @Column(name = "DESCRIPCION")
    private String nombrecat;

	@Column(name = "IMAGEN")
	private String urlImagen;

	@Column(name = "ID_ES")
    private int idEstado;


	public Categoria() {
		super();
	}
    
	public Categoria(int id, String nombrecat, String urlImagen, int idEstado) {
		super();
		this.id = id;
		this.nombrecat = nombrecat;
		this.urlImagen = urlImagen;
		this.idEstado = idEstado;
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

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
   
}
