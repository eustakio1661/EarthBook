package com.earthbook.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIOS")
public class Usuario {

	@Id
	@Column(name = "ID_US")
	public int id;

	@Column(name = "DNI")
	public String dni;

	@Column(name = "NOMBRE")
	public String nombre;

	@Column(name = "APELLIDO")
	public String apellido;

	@Column(name = "TELEFONO")
	public String telefono;

	@Column(name = "DIRECCION")
	public String direccion;

	@Column(name = "CORREO")
	public String correo;

	@Column(name = "CLAVE")
	public String clave;

	@Column(name = "ID_RO")
	public int rol;

	@Column(name = "ID_ES")
	public int estado;

	@Column(name = "IMAGEN")
	public String img;

	public Usuario() {
		super();
	}

	public Usuario(int id, String dni, String nombre, String apellido, String telefono, String direccion, String correo,
			String clave, int rol, int estado, String img) {
		super();
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.direccion = direccion;
		this.correo = correo;
		this.clave = clave;
		this.rol = rol;
		this.estado = estado;
		this.img = img;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono="
				+ telefono + ", direccion=" + direccion + ", correo=" + correo + ", clave=" + clave + ", rol=" + rol
				+ ", estado=" + estado + ", img=" + img + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
}