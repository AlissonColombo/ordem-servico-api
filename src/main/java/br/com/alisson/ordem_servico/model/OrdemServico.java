package br.com.alisson.ordem_servico.model;

//biblioteca para mapear a classe para uma tabela no banco de dados
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/*este comando avisa que a classe cliente é uma entidade do banco de dados, ou seja, 
ela será mapeada para uma tabela no banco de dados com o nome clientes, 
e os atributos da classe serão as colunas da tabela*/ 
@Entity
@Table(name = "tb_ordem_servico")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // gerando o id automaticamente
    private Long id;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataConclusao;

    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemOS> itens = new ArrayList<>();

    @Enumerated(EnumType.STRING) // para armazenar o valor do enum como string no banco de dados
    private StatusOrdem status;

    @ManyToOne // muitos para um, ou seja, várias ordens de serviço podem estar associadas a um cliente
    @JoinColumn(name = "cliente_id") // nome da coluna que fará a referência ao cliente na tabela ordens_servico
    @JsonIgnoreProperties("ordensServico") // para evitar referência circular ao serializar o cliente para JSON, ignorando a lista de ordens de serviço dentro do cliente
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "representante_id")
    @JsonIgnoreProperties("ordensServico") // para evitar referência circular ao serializar o representante para JSON, ignorando a lista de ordens de serviço dentro do representante
    private Representante representante;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnoreProperties({"senha", "representante"}) // Segurança: não envia a senha no JSON
    private Usuario usuarioResponsavel;

    private LocalDateTime dataAtualizacao;

    public OrdemServico() {
    }

    /*
     Método de ciclo de vida do JPA.
     Executado automaticamente antes de salvar uma nova Ordem de Serviço no banco.
     Garante que a data de abertura e o status inicial sejam definidos apenas na criação,
     evitando que dados antigos sejam sobrescritos ao buscar registros existentes.
     */
    @PrePersist
    protected void aoCriar() {
        this.dataAbertura = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
        if (this.status == null) {
            this.status = StatusOrdem.ABERTA;
        }
    }

    @PreUpdate
    protected void aoAtualizar() {
        this.dataAtualizacao = LocalDateTime.now();
    }

    public void adicionarItemOS(ItemOS item) {
        item.setOrdemServico(this); // garante que o item saiba a qual ordem de serviço pertence
        this.itens.add(item);
    }

    public Usuario getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public List<ItemOS> getItens() {
        return itens;
    }
    public void setItens(List<ItemOS> itens) {
        this.itens = itens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDateTime dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public StatusOrdem getStatus() {
        return status;
    }

    public void setStatus(StatusOrdem status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }    

    public Representante getRepresentante() {
        return representante;
    }

    public void setRepresentante(Representante representante) {
        this.representante = representante;
    }
    
}
