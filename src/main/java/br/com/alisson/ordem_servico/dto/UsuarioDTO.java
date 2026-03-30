package br.com.alisson.ordem_servico.dto;

import br.com.alisson.ordem_servico.model.PerfilUsuario;

public class UsuarioDTO {
    
    private String nome;
    private String email;
    private String senha;
    private PerfilUsuario perfil; // ADMIN, GERENTE ou ANALISTA
    private Long representanteId; // Usado apenas quando o ADMIN está criando o usuário
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
    public PerfilUsuario getPerfil() {
        return perfil;
    }
    public void setPerfil(PerfilUsuario perfil) {
        this.perfil = perfil;
    }
    public Long getRepresentanteId() {
        return representanteId;
    }
    public void setRepresentanteId(Long representanteId) {
        this.representanteId = representanteId;
    }

    
}