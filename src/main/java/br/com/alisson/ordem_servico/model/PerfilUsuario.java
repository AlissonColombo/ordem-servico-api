package br.com.alisson.ordem_servico.model;

public enum PerfilUsuario {
    
    ADMIN,      // O dono do sistema (Vê tudo, cria usuários para qualquer representante)
    GERENTE,    // O chefe do representante (Vê tudo da sua "casa", cria usuários para sua casa)
    ANALISTA    // O executor (Cria e preenche a OS do seu representante)

}