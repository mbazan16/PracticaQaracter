package com.practica.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the countries database table.
 * 
 */
@Entity
@Table(name = "countries")
@NamedQuery(name = "Pais.findAll", query = "SELECT p FROM Pais p")
public class Pais implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "country_id")
	private String id;

	@Column(name = "country_name")
	private String nombre;

//	@Column(name = "region_id")
//	private int region;
	
	//TODO: no se como unirlo
	@JoinColumn(name = "region_id")//, referencedColumnName = "region_id") 
	private Region region;

	public Pais() {
	}

	public Pais(String id, String nombre, Region region) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.region = region;
	}
	public Pais(String id, String nombre, int region) {
		super();
		this.id = id;
		this.nombre = nombre;
		(this.region = new Region()).setId(region);
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

}