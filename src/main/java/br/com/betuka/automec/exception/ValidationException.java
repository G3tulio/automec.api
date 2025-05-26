package br.com.betuka.automec.exception;

/**
 * Exceção personalizada para erros de validação de dados.
 */
public class ValidationException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2895313979244089175L;

	/**
     * Construtor padrão com uma mensagem genérica de erro de validação.
     */
    public ValidationException() {
        super("Erro de validação: Dados inválidos.");
    }

    /**
     * Construtor que permite definir uma mensagem personalizada.
     * @param message Mensagem de erro personalizada.
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * Construtor que permite definir uma mensagem e uma causa da exceção.
     * @param message Mensagem de erro.
     * @param cause Causa da exceção.
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}