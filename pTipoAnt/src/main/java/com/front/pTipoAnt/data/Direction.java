package com.front.pTipoAnt.data;

import java.util.Objects;

public class Direction 
{
	private Long id;
	private String street;
	private String postal;
	private String town;
	private String state;
	private String idCountry;
	
	public Direction() { super(); }

	public Direction(Long id, String street, String postal, String town, 
					String state, String idCountry) 
	{
		super();
		this.id = id;
		this.street = street;
		this.postal = postal;
		this.town = town;
		this.state = state;
		this.idCountry = idCountry;
	}

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getStreet() 
	{
		return street;
	}

	public void setStreet(String street) 
	{
		this.street = street;
	}

	public String getPostal() 
	{
		return postal;
	}

	public void setPostal(String postal) 
	{
		this.postal = postal;
	}

	public String getTown() 
	{
		return town;
	}

	public void setTown(String town) 
	{
		this.town = town;
	}

	public String getState() 
	{
		return state;
	}

	public void setState(String state) 
	{
		this.state = state;
	}

	public String getIdCountry() 
	{
		return idCountry;
	}

	public void setIdCountry(String idCountry) 
	{
		this.idCountry = idCountry;
	}

	@Override
	public String toString() 
	{
		return "Direction [id=" + id + ", street=" + street + ", postal=" + 
				postal + ", town=" + town + ", state="
				+ state + ", idCountry=" + idCountry + "]";
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Direction other = (Direction) obj;
		return Objects.equals(id, other.id) 
				&& Objects.equals(idCountry, other.idCountry)
				&& Objects.equals(postal, other.postal) 
				&& Objects.equals(state, other.state)
				&& Objects.equals(street, other.street)
				&& Objects.equals(town, other.town);
	}
	
	
	
	
	
	
	
	
}
