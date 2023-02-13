package com.pratica.jpa.dao.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "regions")
@NamedQuery(name = "Regiao.findAll", query = "SELECT r FROM Regiao r")
public class Regiao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "region_id")
	private int id;

	@Column(name = "region_name")
	private String nome;

	public Regiao() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
