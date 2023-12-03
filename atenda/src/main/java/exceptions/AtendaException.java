package exceptions;

public class AtendaException extends Exception{

	public AtendaException() {
		super();
	}

	public AtendaException(String message, Throwable cause) {
		super(message, cause);

	}

	public AtendaException(String message) {
		super(message);

	}

	public AtendaException(Throwable cause) {
		super(cause);
	}

	
}
