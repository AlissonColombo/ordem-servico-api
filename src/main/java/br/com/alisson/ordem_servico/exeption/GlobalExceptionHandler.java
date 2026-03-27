package br.com.alisson.ordem_servico.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // avisa que ao spring deve usar essa classe para tratar as exceções
public class GlobalExceptionHandler {

    /*avisa que esse método deve ser chamado quando ocorrer a exceção EntidadeNaoEncontradaException
    (tratamento 404)*/
    @ExceptionHandler(EntidadeNaoEncontradaException.class) 
    public ResponseEntity<ErroResposta> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex) {
        ErroResposta erroResposta = new ErroResposta(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroResposta);
    }

    /*tratamento para 400 - Regras de negócio e erros de usuário */
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<ErroResposta> handleNegocio(NegocioException ex) {
        ErroResposta erroResposta = new ErroResposta(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResposta);
    }

}
