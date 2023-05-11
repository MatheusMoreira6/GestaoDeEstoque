package persistencia;

import entidades.ItemSaida;
import java.util.List;

/**
 * @author Matheus
 * @author Gabriel
 */
public class BancoItemSaida extends Banco {

    public static List<ItemSaida> buscarTodos() throws Exception {
        List<ItemSaida> itemSaida;

        itemSaida = Dao.getInstance().getEm().createQuery("Select elemento From ItemSaida elemento").getResultList();

        return (itemSaida);
    }

    public static ItemSaida buscarItemSaida(Integer id) throws Exception {
        ItemSaida itemSaida;

        itemSaida = Dao.getInstance().getEm().find(ItemSaida.class, id);

        return (itemSaida);
    }

}
