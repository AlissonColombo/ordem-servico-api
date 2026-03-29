package br.com.alisson.ordem_servico.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;


@Entity//comunicando para o Java e Hibernet que essa classe é uma tabela no banco de dados
@Table(name = "tb_item_os")
public class ItemOS {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // comando para que o campo seja obrigatório no banco de dados,lancara uma excessao antes mesmo de enviar o comando para o banco de dados, caso o campo esteja vazio
    private String descricaoProblema;
    private String descricaoSolucao;

    @JsonIgnore //Quando formos lancar o Json para o frontend, ele vai ignorar esse campo, para evitar um loop infinito, pois a OrdemServico tem uma lista de ItemOS e cada ItemOS tem uma OrdemServico, e assim por diante, causando um loop infinito de chamadas entre as duas classes
    @ManyToOne// relacao de muitos para um, ou seja, varios itens de ordem de servico podem estar associados a uma ordem de servico
    @JoinColumn(name = "ordem_servico_id")// nome da coluna que fará a referencia a ordem de servico na tabela item_os, ou seja, essa coluna vai armazenar o id da ordem de servico associada a cada item de ordem de servico
    private OrdemServico ordemServico;

    public ItemOS() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public void setDescricaoProblema(String descricaoProblema) {
        this.descricaoProblema = descricaoProblema;
    }

    public String getDescricaoSolucao() {
        return descricaoSolucao;
    }

    public void setDescricaoSolucao(String descricaoSolucao) {
        this.descricaoSolucao = descricaoSolucao;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }   

    

}
