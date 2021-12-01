package com.earthbook.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONTACTOS")
public class Contactos {
	
	@Id
	@Column(name = "ID_CON")
	private int codigo;

	@Column(name = "NOM_CON")
	private String nombre;

	@Column(name = "CORREO_CON")
	private String correo;

	@Column(name = "MSJ_CON")
	private String mensaje;
	
	public Contactos() {
		super();
	}
	
	

	@Override
	public String toString() {
		return "Contactos [codigo=" + codigo + ", nombre=" + nombre + ", correo=" + correo + ", mensaje=" + mensaje
				+ "]";
	}



	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	

}
