package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Matheus
 * @author Gabriel
 */
@Entity
public class ItemEntrada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Produto produto;

    private Integer quantidade;

    @ManyToOne
    private EstoqueEntrada estoqueEntrada;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public EstoqueEntrada getEstoqueEntrada() {
        return estoqueEntrada;
    }

    public void setEstoqueEntrada(EstoqueEntrada estoqueEntrada) {
        this.estoqueEntrada = estoqueEntrada;
    }

    @Override
    public String toString() {
        return (String.format("%d", id));
    }

}
