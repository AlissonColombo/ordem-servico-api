package br.com.alisson.ordem_servico.model;

//biblioteca para mapear a classe para uma tabela no banco de dados
import jakarta.persistence.*;
import java.time.LocalDateTime;

/*este comando avisa que a classe cliente é uma entidade do banco de dados, ou seja, 
ela será mapeada para uma tabela no banco de dados com o nome clientes, 
e os atributos da classe serão as colunas da tabela*/ 
@Entity
@Table(name = "tb_ordem_servico")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // gerando o id automaticamente
    private Long id;
    private String descricaoProblema;
    private String descricaoSolucao;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataConclusao;

    @Enumerated(EnumType.STRING) // para armazenar o valor do enum como string no banco de dados
    private StatusOrdem status;

    @ManyToOne // muitos para um, ou seja, várias ordens de serviço podem estar associadas a um cliente
    @JoinColumn(name = "cliente_id") // nome da coluna que fará a referência ao cliente na tabela ordens_servico
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "representante_id")
    private Representante representante;

    public OrdemServico() {
        this.dataAbertura = LocalDateTime.now(); // registrando a data de abertura automaticamente
        this.status = StatusOrdem.ABERTA; // definindo o status inicial
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
