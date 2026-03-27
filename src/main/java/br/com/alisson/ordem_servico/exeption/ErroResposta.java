package br.com.alisson.ordem_servico.exeption;

import java.time.LocalDateTime;

public class ErroResposta {
    private int status;
    private String mensagem;
    private LocalDateTime timestamp;

    public ErroResposta(int status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
        this.timestamp = LocalDateTime.now();
    }

    public int getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
