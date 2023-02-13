package com.evaluacion.practicaNicolas.dao.exceptions;

@SuppressWarnings("serial")
public class DAOException extends Exception {
	public DAOException(Exception e) {
		super(e.getMessage());
	}
}
