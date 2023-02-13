package com.evaluacion.practicaNicolas.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the countries database table.
 * 
 */
@Entity
@Table(name="countries")
@NamedQuery(name="Country.findAll", query="SELECT p FROM Pais p")
public class Pais implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="country_id")
	private String id;

	@Column(name="country_name")
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "region_id")
	private Region region;

	public Pais() {
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

	public void setRegion(Region regionId) {
		this.region = regionId;
	}

}