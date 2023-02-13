package com.ejemplo.practicaruben.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the regions database table.
 * 
 */
@Entity
@Table(name="regions")
@NamedQuery(name="Region.findAll", query="SELECT r FROM Region r")
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="region_id")
	private int idRegion;

	@Column(name="region_name")
	private String nombre;

	public Region() {
	}
	
	public Region(int id, String nombre) {
		this.idRegion = id;
		this.nombre = nombre;
	}

	public int getIdRegion() {
		return this.idRegion;
	}

	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}