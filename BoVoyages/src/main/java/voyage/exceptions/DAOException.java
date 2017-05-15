package voyage.exceptions;

/**
 * Classe de gestion des exceptions métier.
 * 
 * @author Adminl
 * @version 3.0
 */
public class DAOException extends Exception {
	private static final long serialVersionUID = -4131743238757727727L;

	public DAOException() {
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
