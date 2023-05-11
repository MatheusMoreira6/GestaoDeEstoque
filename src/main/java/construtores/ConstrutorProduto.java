package construtores;

import entidades.Categoria;
import entidades.Marca;
import entidades.Produto;

/**
 * @author Matheus
 * @author Gabriel
 */
public class ConstrutorProduto {

    private final String nome;
    private final Marca marca;
    private final Integer garantia;
    private final Integer quantidade;
    private final Categoria categoria;
    private final Float preco;
    private final Integer id;

    public ConstrutorProduto(String nome, Marca marca, Integer garantia, Integer quantidade, Categoria categoria, Float preco, Integer id) {
        this.nome = nome;
        this.marca = marca;
        this.garantia = garantia;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.preco = preco;
        this.id = id;
    }

    private ConstrutorProduto validar() throws Exception {
        if (id == null) {
            throw new Exception();
        } else if (nome.isBlank() || nome.isEmpty()) {
            throw new Exception();
        } else if (marca == null) {
            throw new Exception();
        } else if (garantia == null) {
            throw new Exception();
        } else if (quantidade == null) {
            throw new Exception();
        } else if (categoria == null) {
            throw new Exception();
        } else if (preco == null) {
            throw new Exception();
        }

        return (this);
    }

    public Produto construir() throws Exception {
        validar();

        Produto produto = new Produto();
        produto.setId(id);
        produto.setNome(nome);
        produto.setMarca(marca);
        produto.setGarantia(garantia);
        produto.setQuantidade(quantidade);
        produto.setCategoria(categoria);
        produto.setPreco(preco);

        return (produto);
    }
}
