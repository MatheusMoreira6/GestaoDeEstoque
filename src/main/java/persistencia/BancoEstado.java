package persistencia;

import entidades.Estado;
import java.util.List;
import javax.persistence.Query;

/**
 * @author Matheus
 * @author Gabriel
 */
public class BancoEstado extends Banco {

    public static List<Estado> buscarTodos() throws Exception {
        List<Estado> estado;

        estado = Dao.getInstance().getEm().createQuery("Select elemento From Estado elemento").getResultList();

        return (estado);
    }

    public static Estado buscarEstado(Integer id) throws Exception {
        Estado estado;

        estado = Dao.getInstance().getEm().find(Estado.class, id);

        return (estado);
    }

    public static List<Estado> buscarEstado(Estado estado) throws Exception {
        Integer verificador = 0;
        List<Estado> estados;
        String sql = "Select elemento From Estado elemento Where 1=1 ";

        if (!estado.getNome().isBlank() || !estado.getNome().isEmpty()) {
            sql += "and elemento.nome = :nome ";
        } else {
            verificador++;
        }

        if (verificador == 1) {
            throw new Exception();
        } else {
            Query query = Dao.getInstance().getEm().createQuery(sql);

            if (!estado.getNome().isBlank() || !estado.getNome().isEmpty()) {
                query.setParameter("nome", estado.getNome());
            }

            estados = query.getResultList();

            return (estados);
        }
    }
}
