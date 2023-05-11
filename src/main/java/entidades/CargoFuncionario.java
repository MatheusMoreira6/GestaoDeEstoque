package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author Matheus
 * @author Gabriel
 */
@Entity
public class CargoFuncionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 256)
    private String cargo;

    private Boolean funcoesAdministrador;

    @OneToMany(mappedBy = "cargo")
    private List<Funcionario> funcionario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Boolean getFuncoesAdministrador() {
        return funcoesAdministrador;
    }

    public void setFuncoesAdministrador(Boolean funcoesAdministrador) {
        this.funcoesAdministrador = funcoesAdministrador;
    }

    public List<Funcionario> getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(List<Funcionario> funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public String toString() {
        return cargo;
    }

}
