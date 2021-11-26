package com.earthbook.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EDITORIALES")
public class Editorial {

    @Id
    @Column(name = "ID_ED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "DESCRIPCION")
    private String descripcion;
    
	@Column(name = "ID_ES")
    private int idEstado;

	
	
    public Editorial(int id, String descripcion, int idEstado) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.idEstado = idEstado;
	}

	public Editorial() {
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

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

    
   
}
