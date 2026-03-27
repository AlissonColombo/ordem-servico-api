package br.com.alisson.ordem_servico.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_representante")
public class Representante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int codigoSAP;

    @Embedded 
    private Endereco endereco;

    public Representante() {
    
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigoSAP() {
        return codigoSAP;
    }

    public void setCodigoSAP(int codigoSAP) {
        this.codigoSAP = codigoSAP;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    } 

    
}
