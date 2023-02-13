package exceptions;

@SuppressWarnings("serial")
public class DAOExceptions extends Exception {

	TipoExceptions tipoExcepcion;

	public DAOExceptions(TipoExceptions tipoExcepcion) {
		super();
		this.tipoExcepcion = tipoExcepcion;
	}

	public DAOExceptions(Exception e) {
		super(e);
	}

	public TipoExceptions getTipoExcepcion() {
		return tipoExcepcion;
	}

}
