package com.practica.lucioalonso.entities;

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
	private int regionId;

	@Column(name="region_name")
	private String regionNombre;

	public Region() {
	}


	public Region(int regionId, String regionNombre) {
		super();
		this.regionId = regionId;
		this.regionNombre = regionNombre;
	}



	public int getRegionId() {
		return this.regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public String getRegionNombre() {
		return this.regionNombre;
	}

	public void setRegionNombre(String regionNombre) {
		this.regionNombre = regionNombre;
	}


	@Override
	public String toString() {
		return "Region [regionId=" + regionId + ", regionNombre=" + regionNombre + "]";
	}
	
	

}