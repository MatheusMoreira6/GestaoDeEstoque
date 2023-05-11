package persistencia;

import entidades.ItemEntrada;
import java.util.List;
import javax.persistence.Query;

/**
 * @author Matheus
 * @author Gabriel
 */
public class BancoItemEntrada extends Banco {

    public static List<ItemEntrada> buscarTodos() throws Exception {
        List<ItemEntrada> itemEntrada;

        itemEntrada = Dao.getInstance().getEm().createQuery("Select elemento From ItemEntrada elemento").getResultList();

        return (itemEntrada);
    }

    public static ItemEntrada buscarItemEntrada(Integer id) throws Exception {
        ItemEntrada itemEntrada;

        itemEntrada = Dao.getInstance().getEm().find(ItemEntrada.class, id);

        return (itemEntrada);
    }

    public static List<ItemEntrada> buscarItemEntrada(ItemEntrada itemEntrada) throws Exception {
        Boolean verificador = false;
        List<ItemEntrada> itensEntrada;
        String sql = "Select elemento From ItemEntrada elemento Where 1=1 ";

        if (itemEntrada.getEstoqueEntrada() != null) {
            sql += "and elemento.estoqueentrada = :estoqueentrada";

            verificador = true;
        }

        if (verificador) {
            Query query = Dao.getInstance().getEm().createQuery(sql);

            query.setParameter("estoqueentrada", itemEntrada.getEstoqueEntrada());

            itensEntrada = query.getResultList();

            return (itensEntrada);
        } else {
            throw new Exception();
        }
    }

}
