package br.com.alisson.ordem_servico.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String email;
    private String senha;
    
    private boolean ativo = true;

    @Enumerated(EnumType.STRING)
    private PerfilUsuario perfil; // ADMIN ou ANALISTA

    @ManyToOne
    @JoinColumn(name = "representante_id", nullable = false)
    private Representante representante;

    public Usuario() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilUsuario perfil) {
        this.perfil = perfil;
    }

    public Representante getRepresentante() {
        return representante;
    }

    public void setRepresentante(Representante representante) {
        this.representante = representante;
    }

    
}