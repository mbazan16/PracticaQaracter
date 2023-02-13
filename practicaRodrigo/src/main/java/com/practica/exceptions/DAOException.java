package com.practica.exceptions;


public class DAOException extends Exception {

	TipoException tipoExcepcion;

	public DAOException(TipoException tipoExcepcion) {
		super();
		this.tipoExcepcion = tipoExcepcion;
	}

	public DAOException(Exception e) {
		super(e);
	}

	public TipoException getTipoExcepcion() {
		return tipoExcepcion;
	}
}
