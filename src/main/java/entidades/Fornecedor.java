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
public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 256)
    private String razaoSocial;

    @Column(length = 256)
    private String nomeFantasia;

    @Column(length = 14)
    private String cnpj;

    @Column(length = 256)
    private String inscricaoEstadual;

    @Column(length = 22)
    private String telefone;

    @Column(length = 256)
    private String email;

    @ManyToOne
    private Endereco endereco;

    @OneToMany(mappedBy = "fornecedor")
    private List<EstoqueEntrada> estoqueEntrada;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public List<EstoqueEntrada> getEstoqueEntrada() {
        return estoqueEntrada;
    }

    public void setEstoqueEntrada(List<EstoqueEntrada> estoqueEntrada) {
        this.estoqueEntrada = estoqueEntrada;
    }

    @Override
    public String toString() {
        return nomeFantasia;
    }

}
