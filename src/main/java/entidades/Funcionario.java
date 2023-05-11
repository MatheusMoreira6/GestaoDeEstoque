package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author Matheus
 * @author Gabriel
 */
@Entity
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 256)
    private String nome;

    @Column(length = 15)
    private String cpf;

    @ManyToOne
    private CargoFuncionario cargo;

    @Column(length = 256)
    private String login;

    private String senha;

    @OneToMany(mappedBy = "funcionario")
    private List<EstoqueEntrada> estoqueEntrada;

    @OneToMany(mappedBy = "funcionario")
    private List<EstoqueSaida> estoqueSaida;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public CargoFuncionario getCargo() {
        return cargo;
    }

    public void setCargo(CargoFuncionario cargo) {
        this.cargo = cargo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<EstoqueEntrada> getEstoqueEntrada() {
        return estoqueEntrada;
    }

    public void setEstoqueEntrada(List<EstoqueEntrada> estoqueEntrada) {
        this.estoqueEntrada = estoqueEntrada;
    }

    public List<EstoqueSaida> getEstoqueSaida() {
        return estoqueSaida;
    }

    public void setEstoqueSaida(List<EstoqueSaida> estoqueSaida) {
        this.estoqueSaida = estoqueSaida;
    }

    @Override
    public String toString() {
        return nome;
    }

}
