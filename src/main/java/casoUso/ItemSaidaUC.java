package casoUso;

import construtores.ConstrutorItemSaida;
import entidades.ItemSaida;
import java.util.List;
import persistencia.BancoItemSaida;

/**
 * @author Matheus
 * @author Gabriel
 */
public class ItemSaidaUC {

    private ItemSaidaUC() {

    }

    public static ItemSaida salvarItemSaida(ConstrutorItemSaida itemSaida) throws Exception {
        return ((ItemSaida) BancoItemSaida.salvar(itemSaida.construir()));
    }

    public static void removerItemSaida(ItemSaida itemSaida) throws Exception {
        BancoItemSaida.remover(itemSaida);
    }

    public static List<ItemSaida> buscarTodos() throws Exception {
        return (BancoItemSaida.buscarTodos());
    }

    public static ItemSaida buscarItemSaida(Integer id) throws Exception {
        return (BancoItemSaida.buscarItemSaida(id));
    }

}
