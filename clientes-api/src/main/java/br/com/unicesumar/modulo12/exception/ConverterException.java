package br.com.unicesumar.modulo12.exception;

public class ConverterException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ConverterException(String mensagem) {
		super(mensagem);
	}

}
