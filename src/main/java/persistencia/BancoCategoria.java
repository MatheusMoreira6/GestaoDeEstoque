package persistencia;

import entidades.Categoria;
import java.util.List;
import javax.persistence.Query;

/**
 * @author Matheus
 * @author Gabriel
 */
public class BancoCategoria extends Banco {

    public static List<Categoria> buscarTodos() throws Exception {
        List<Categoria> categoria;

        categoria = Dao.getInstance().getEm().createQuery("Select elemento From Categoria elemento").getResultList();

        return (categoria);
    }

    public static Categoria buscarCategoria(Integer id) throws Exception {
        Categoria categoria;

        categoria = Dao.getInstance().getEm().find(Categoria.class, id);

        return (categoria);
    }

    public static List<Categoria> buscarCategoria(Categoria categoria) throws Exception {
        Integer verificador = 0;
        List<Categoria> categorias;
        String sql = "Select elemento From Categoria elemento Where 1=1 ";

        if (!categoria.getNome().isBlank() || !categoria.getNome().isEmpty()) {
            sql += "and elemento.nome = :nome";
        } else {
            verificador++;
        }

        if (verificador == 1) {
            throw new Exception();
        } else {
            Query query = Dao.getInstance().getEm().createQuery(sql);

            query.setParameter("nome", categoria.getNome());

            categorias = query.getResultList();

            return (categorias);
        }
    }
}
