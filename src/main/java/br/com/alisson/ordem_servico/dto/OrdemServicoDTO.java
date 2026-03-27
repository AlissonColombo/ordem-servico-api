package br.com.alisson.ordem_servico.dto;

public class OrdemServicoDTO {
    
    private Long clienteId;
    private Long ordemId;
    private String descricaoProblema;
    private String descricaoSolucao;
    private String status;


    public Long getClienteId() {
        return clienteId;
    }
    public Long getOrdemId() {
        return ordemId;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public String getDescricaoSolucao() {
        return descricaoSolucao;
    }

    public String getStatus() {
        return status;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    
    public void setOrdemId(Long ordemId) {
        this.ordemId = ordemId;
    }

    public void setDescricaoProblema(String descricaoProblema) {
        this.descricaoProblema = descricaoProblema;
    }

    public void setDescricaoSolucao(String descricaoSolucao) {
        this.descricaoSolucao = descricaoSolucao;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
