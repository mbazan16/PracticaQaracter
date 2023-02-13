package com.front.pTipoAnt.data;

import java.util.Objects;

/**
 * Bean Departamento
 * @author MARIA
 *
 */
public class Departamento {
	private Long id;
	private String nombre;
	private Long idManager;
	private Direction direction;
	
	public Departamento() {
		super();
	}

	public Departamento(Long id, String nombre, Long idManager, Direction direction) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.idManager = idManager;
		this.direction = direction;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdManager() {
		return idManager;
	}

	public void setIdManager(Long idManager) {
		this.idManager = idManager;
	}

	

	public Direction getDirection() {
		if(this.direction == null) this.direction = new Direction();
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "Departamento [id=" + id + ", nombre=" + nombre + ", idManager=" + idManager + ", direction=" + direction
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(direction, id, idManager, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		return Objects.equals(direction, other.direction) && Objects.equals(id, other.id)
				&& Objects.equals(idManager, other.idManager) && Objects.equals(nombre, other.nombre);
	}

	
	
}
