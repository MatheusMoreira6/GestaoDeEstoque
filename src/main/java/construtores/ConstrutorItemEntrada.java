package construtores;

import entidades.EstoqueEntrada;
import entidades.ItemEntrada;
import entidades.Produto;

/**
 * @author Matheus
 * @author Gabriel
 */
public class ConstrutorItemEntrada {

    private final Produto produto;
    private final Integer quantidade;
    private final EstoqueEntrada estoqueEntrada;

    public ConstrutorItemEntrada(Produto produto, Integer quantidade, EstoqueEntrada estoqueEntrada) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.estoqueEntrada = estoqueEntrada;
    }

    private ConstrutorItemEntrada validar() throws Exception {
        if (produto == null) {
            throw new Exception();
        } else if (quantidade == null) {
            throw new Exception();
        } else if (estoqueEntrada == null) {
            throw new Exception();
        }

        return (this);
    }

    public ItemEntrada construir() throws Exception {
        validar();

        ItemEntrada itemEntrada = new ItemEntrada();
        itemEntrada.setProduto(produto);
        itemEntrada.setQuantidade(quantidade);
        itemEntrada.setEstoqueEntrada(estoqueEntrada);

        return (itemEntrada);
    }
}
