package com.practica.lucioalonso.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the countries database table.
 * 
 */
@Entity
@Table(name="countries")
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="country_id")
	private String countryId;

	@Column(name="country_name")
	private String countryName;

	@Column(name="region_id")
	private int regionId;

	public Country() {
	}
	
	public Country(String countryId, String countryName, int regionId) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
		this.regionId = regionId;
	}

	public String getCountryId() {
		return this.countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public int getRegionId() {
		return this.regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", countryName=" + countryName + ", regionId=" + regionId + "]";
	}
	
	

}