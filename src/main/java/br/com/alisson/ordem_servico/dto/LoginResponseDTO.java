package br.com.alisson.ordem_servico.dto;

public class LoginResponseDTO {
    private String token; // O JWT para as próximas requisições
    private String nome;
    private String perfil; // ADMIN ou ANALISTA
    private String representanteNome; // Para mostrar "Bem-vindo à Empresa X" no Flutter
    
    public LoginResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getRepresentanteNome() {
        return representanteNome;
    }

    public void setRepresentanteNome(String representanteNome) {
        this.representanteNome = representanteNome;
    }
}