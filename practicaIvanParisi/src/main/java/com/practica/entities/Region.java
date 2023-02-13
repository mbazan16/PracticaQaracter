package com.practica.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the regions database table.
 * 
 */
@Entity
@Table(name="regions")
@NamedQuery(name="Regiones.findAll", query="SELECT r FROM Region r")
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="region_id")
	private int id;

	@Column(name="region_name")
	private String nombreRegion;

	public Region() {
	}
	
	

	public Region(int id, String nombreRegion) {
		super();
		this.id = id;
		this.nombreRegion = nombreRegion;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreRegion() {
		return this.nombreRegion;
	}

	public void setNombreRegion(String nombreRegion) {
		this.nombreRegion = nombreRegion;
	}

}