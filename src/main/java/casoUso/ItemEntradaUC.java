package casoUso;

import construtores.ConstrutorItemEntrada;
import entidades.ItemEntrada;
import java.util.List;
import persistencia.BancoItemEntrada;

/**
 * @author Matheus
 * @author Gabriel
 */
public class ItemEntradaUC {

    private ItemEntradaUC() {

    }

    public static ItemEntrada salvarItemEntrada(ConstrutorItemEntrada itemEntrada) throws Exception {
        return ((ItemEntrada) BancoItemEntrada.salvar(itemEntrada.construir()));
    }

    public static void removerItemEntrada(ItemEntrada itemEntrada) throws Exception {
        BancoItemEntrada.remover(itemEntrada);
    }

    public static List<ItemEntrada> buscarTodos() throws Exception {
        return (BancoItemEntrada.buscarTodos());
    }

    public static ItemEntrada buscarItemEntrada(Integer id) throws Exception {
        return (BancoItemEntrada.buscarItemEntrada(id));
    }

}
