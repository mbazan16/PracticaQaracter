package com.paises.exceptions;


@SuppressWarnings("serial")
public class DAOException extends Exception {

	public DAOException() {
		super();
	}

	public DAOException(Exception e) {
		super(e.getMessage());
	}

	
	
}
