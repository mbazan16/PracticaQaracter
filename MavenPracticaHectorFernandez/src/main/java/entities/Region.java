package entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

/**
 * The persistent class for the regions database table.
 * 
 */
@Entity
@Table(name = "regions")
@NamedQuery(name = "Region.findAll", query = "SELECT r FROM Region r")
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "region_id")
	private int Id;

	private String region_Name;

	public Region() {
	}

	public Region(int id, String region_Name) {
		super();
		Id = id;
		this.region_Name = region_Name;
	}

	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	public String getRegion_Name() {
		return this.region_Name;
	}

	public void setRegion_Name(String region_Name) {
		this.region_Name = region_Name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id, region_Name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Region other = (Region) obj;
		return Id == other.Id && Objects.equals(region_Name, other.region_Name);
	}

	@Override
	public String toString() {
		return "Region [Id=" + Id + ", region_Name=" + region_Name + "]";
	}

}