package com.practica.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the countries database table.
 * 
 */
@Entity
@Table(name="countries")
@NamedQuery(name="Pais.findAll", query="SELECT p FROM Pais p")
public class Pais implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="country_id")
	private int id;

	@Column(name="country_name")
	private String paisNombre;

	@OneToOne
	@JoinColumn(name = "region_id")
	private Region region;

	public Pais() {
	}

	
	public Pais(int id, String paisNombre, Region region) {
		super();
		this.id = id;
		this.paisNombre = paisNombre;
		this.region = region;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPaisNombre() {
		return this.paisNombre;
	}

	public void setPaisNombre(String paisNombre) {
		this.paisNombre = paisNombre;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) 
	{
		this.region = region;
	}

	

}