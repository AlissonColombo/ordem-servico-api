package br.com.alisson.ordem_servico.dto;


public class ClienteDTO {
    
    private Long clienteId;
    private String nome;
    private String cnpj;   
    private String telefone;
    private EnderecoDTO endereco;
    private Long representanteId;   


    public Long getRepresentanteId() {
        return representanteId;
    }

    public void setRepresentanteId(Long representanteId) {
        this.representanteId = representanteId;
    }

    public Long getClienteId() {
        return clienteId;
    }
    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

}
