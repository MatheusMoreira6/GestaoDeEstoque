package casoUso;

import construtores.ConstrutorProduto;
import entidades.Produto;
import java.util.List;
import persistencia.BancoProduto;

/**
 * @author Matheus
 * @author Gabriel
 */
public class ProdutoUC {

    private ProdutoUC() {

    }

    public static Produto salvarProduto(ConstrutorProduto produto) throws Exception {
        return ((Produto) BancoProduto.salvar(produto.construir()));
    }

    public static void removerProduto(Produto produto) throws Exception {
        BancoProduto.remover(produto);
    }

    public static List<Produto> buscarTodos() throws Exception {
        return (BancoProduto.buscarTodos());
    }

    public static Produto buscarProduto(Integer id) throws Exception {
        return (BancoProduto.buscarProduto(id));
    }

    public static List<Produto> buscarProduto(ConstrutorProduto produto) throws Exception {
        return (BancoProduto.buscarProduto(produto.construir()));
    }

}
