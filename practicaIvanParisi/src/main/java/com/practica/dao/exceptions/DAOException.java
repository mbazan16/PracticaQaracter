package com.practica.dao.exceptions;


/**
 * Clase propia que recoge las excepciones del DAO
 * @author MARIA
 *
 */
@SuppressWarnings("serial")
public class DAOException extends Exception {

	public DAOException(Exception e) {
		super(e.getMessage());
	}



	
}
