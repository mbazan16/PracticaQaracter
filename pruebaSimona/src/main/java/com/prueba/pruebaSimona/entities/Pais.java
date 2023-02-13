package com.prueba.pruebaSimona.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the countries database table.
 * 
 */
@Entity
@Table(name="countries")
@NamedQuery(name="Pais.findAll", query="SELECT c FROM Pais c")
public class Pais implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="country_id")
	private String id;

	@Column(name="country_name")
	private String nombrePais;

	@ManyToOne
	@JoinColumn(name = "id", referencedColumnName = "region_id")
	private Region region;

	public Pais() {
	}

	public Pais(String id, String nombrePais, Region region) {
		super();
		this.id = id;
		this.nombrePais = nombrePais;
		this.region = region;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombrePais() {
		return this.nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	

}