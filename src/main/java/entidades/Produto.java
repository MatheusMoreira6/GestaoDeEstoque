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
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 256)
    private String nome;

    @ManyToOne
    private Marca marca;

    private Integer garantia;

    private Integer quantidade;

    @ManyToOne
    private Categoria categoria;

    @Column(precision = 9, scale = 2)
    private Float preco;

    @OneToMany(mappedBy = "produto")
    private List<ItemEntrada> itemEntrada;

    @OneToMany(mappedBy = "produto")
    private List<ItemSaida> itemSaida;

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

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Integer getGarantia() {
        return garantia;
    }

    public void setGarantia(Integer garantia) {
        this.garantia = garantia;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public List<ItemEntrada> getItemEntrada() {
        return itemEntrada;
    }

    public void setItemEntrada(List<ItemEntrada> itemEntrada) {
        this.itemEntrada = itemEntrada;
    }

    public List<ItemSaida> getItemSaida() {
        return itemSaida;
    }

    public void setItemSaida(List<ItemSaida> itemSaida) {
        this.itemSaida = itemSaida;
    }

    @Override
    public String toString() {
        return nome;
    }

}
