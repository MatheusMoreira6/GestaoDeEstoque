package persistencia;

import entidades.Marca;
import java.util.List;
import javax.persistence.Query;

/**
 * @author Matheus
 * @author Gabriel
 */
public class BancoMarca extends Banco {

    public static List<Marca> buscarTodos() throws Exception {
        List<Marca> marca;

        marca = Dao.getInstance().getEm().createQuery("Select elemento From Marca elemento").getResultList();

        return (marca);
    }

    public static Marca buscarMarca(Integer id) throws Exception {
        Marca marca;

        marca = Dao.getInstance().getEm().find(Marca.class, id);

        return (marca);
    }

    public static List<Marca> buscarMarca(Marca marca) throws Exception {
        Integer verificador = 0;
        List<Marca> marcas;
        String sql = "Select elemento From Marca elemento Where 1=1 ";

        if (!marca.getNome().isBlank() || !marca.getNome().isEmpty()) {
            sql += "and elemento.nome = :nome";
        } else {
            verificador++;
        }

        if (verificador == 1) {
            throw new Exception();
        } else {
            Query query = Dao.getInstance().getEm().createQuery(sql);

            query.setParameter("nome", marca.getNome());

            marcas = query.getResultList();

            return (marcas);
        }
    }

}
