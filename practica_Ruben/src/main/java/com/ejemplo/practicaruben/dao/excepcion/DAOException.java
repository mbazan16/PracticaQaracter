package com.ejemplo.practicaruben.dao.excepcion;

import com.ejemplo.practicaruben.dao.excepcion.TipoException;
/**
 * Clase propia que recoge las excepciones del DAO
 * @author MARIA
 *
 */
@SuppressWarnings("serial")
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
