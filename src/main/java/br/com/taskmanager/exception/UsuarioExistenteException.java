package br.com.taskmanager.exception;

public class UsuarioExistenteException extends Exception{

	private static final long serialVersionUID = 1L;

	public UsuarioExistenteException(String mensagem) {
		super(mensagem);
	}
	
}
