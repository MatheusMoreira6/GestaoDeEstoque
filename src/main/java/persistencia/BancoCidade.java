package persistencia;

import entidades.Cidade;
import java.util.List;
import javax.persistence.Query;

/**
 * @author Matheus
 * @author Gabriel
 */
public class BancoCidade extends Banco {

    public static List<Cidade> buscarTodos() throws Exception {
        List<Cidade> cidade;

        cidade = Dao.getInstance().getEm().createQuery("Select elemento From Cidade elemento").getResultList();

        return (cidade);
    }

    public static Cidade buscarCidade(Integer id) throws Exception {
        Cidade cidade;

        cidade = Dao.getInstance().getEm().find(Cidade.class, id);

        return (cidade);
    }

    public static List<Cidade> buscarCidade(Cidade cidade) throws Exception {
        Integer verificador = 0;
        List<Cidade> cidades;
        String sql = "Select elemento From Cidade elemento Where 1=1 ";

        if (!cidade.getNome().isBlank() || !cidade.getNome().isEmpty()) {
            sql += "and elemento.nome = :nome";
        } else {
            verificador++;
        }

        if (verificador == 1) {
            throw new Exception();
        } else {
            Query query = Dao.getInstance().getEm().createQuery(sql);

            query.setParameter("nome", cidade.getNome());

            cidades = query.getResultList();

            return (cidades);
        }
    }

}
