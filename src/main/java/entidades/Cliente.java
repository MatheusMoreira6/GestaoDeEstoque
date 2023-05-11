package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 * @author Matheus
 * @author Gabriel
 */
@Entity
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 256)
    private String nome;

    @Column(length = 14)
    private String cpf;

    @Column(length = 22)
    private String telefone;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataNascimento;

    @Column(length = 256)
    private String email;

    @ManyToOne
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente")
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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
