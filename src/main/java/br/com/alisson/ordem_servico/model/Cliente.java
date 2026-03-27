package br.com.alisson.ordem_servico.model;


import java.util.List;
import java.util.ArrayList;

//biblioteca para mapear a classe cliente para uma tabela no banco de dados
import jakarta.persistence.*;


/*este comando avisa que a classe cliente é uma entidade do banco de dados, ou seja, 
ela será mapeada para uma tabela no banco de dados com o nome clientes, 
e os atributos da classe serão as colunas da tabela*/    
@Entity

@Table(name = "tb_clientes")
public class Cliente {

    /*informando que o atributo id é a chave primária da tabela, e que seu valor será gerado
    automaticamente pelo banco de dados, utilizando a estratégia de auto-incremento*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // gerando o id automaticamente
    private Long id;
    private String nome;
    private String cnpj;
    private String telefone;

    /*avisando que o atributo endereco é um objeto embutido na tabela clientes, ou seja,
    seus atributos serão colunas da tabela clientes */   
    @Embedded
    private Endereco endereco;

    /*informando que o atributo ordensServico é uma relação de um para muitos com a tabela ordens_servico, 
    ou seja, um cliente pode ter várias ordens de serviço */    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    /* se excluir um cliente, excluir suas ordens de serviço */
    private List<OrdemServico> ordensServico = new ArrayList<>();

    public Cliente() {
    
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<OrdemServico> getOrdensServico() {
        return ordensServico;
    }

    public void setOrdensServico(List<OrdemServico> ordensServico) {
        this.ordensServico = ordensServico;
    }

    

}
