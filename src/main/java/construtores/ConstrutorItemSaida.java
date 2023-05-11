package construtores;

import entidades.EstoqueSaida;
import entidades.ItemSaida;
import entidades.Produto;

/**
 * @author Matheus
 * @author Gabriel
 */
public class ConstrutorItemSaida {

    private final Produto produto;
    private final Integer quantidade;
    private final EstoqueSaida estoqueSaida;

    public ConstrutorItemSaida(Produto produto, Integer quantidade, EstoqueSaida estoqueSaida) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.estoqueSaida = estoqueSaida;
    }

    private ConstrutorItemSaida validar() throws Exception {
        if (produto == null) {
            throw new Exception();
        } else if (quantidade == null) {
            throw new Exception();
        } else if (estoqueSaida == null) {
            throw new Exception();
        }

        return (this);
    }

    public ItemSaida construir() throws Exception {
        validar();

        ItemSaida itemSaida = new ItemSaida();
        itemSaida.setProduto(produto);
        itemSaida.setQuantidade(quantidade);
        itemSaida.setEstoqueSaida(estoqueSaida);

        return (itemSaida);
    }
}
