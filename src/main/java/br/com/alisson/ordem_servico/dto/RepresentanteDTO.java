package br.com.alisson.ordem_servico.dto;

public class RepresentanteDTO {
    
    private String nome;
    private int codigoSAP;
    private EnderecoDTO endereco;

    
    public String getNome() {
        return nome;
    }

    public int getCodigoSAP() {
        return codigoSAP;
    }

      public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodigoSAP(int codigoSAP) {
        this.codigoSAP = codigoSAP;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }
}
