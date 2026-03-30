package br.com.alisson.ordem_servico.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    
    @OneToMany(mappedBy = "representante", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Cliente> clientes = new ArrayList<>();

    public Representante() {
    
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
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
