package com.ejemplo.practicaruben.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


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
	private String idPais;

	@Column(name="country_name")
	private String nombre;

	@ManyToOne
	@JoinColumn(name="region_id", referencedColumnName = "region_id")
	private Region region;

	public Pais() {
	}
	
	public Pais(String id, String nombre, Region reg) {
		this.idPais = id;
		this.nombre = nombre;
		this.region = reg;
	}

	public String getIdPais() {
		return this.idPais;
	}

	public void setIdPais(String idPais) {
		this.idPais = idPais;
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