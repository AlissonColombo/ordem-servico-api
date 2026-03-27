package br.com.alisson.ordem_servico.exeption;

public class NegocioException extends RuntimeException {
    public NegocioException(String message) {
        super(message);
    }
}
