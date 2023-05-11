package persistencia;

import entidades.EstoqueSaida;
import java.util.List;
import javax.persistence.Query;

/**
 * @author Matheus
 * @author Gabriel
 */
public class BancoEstoqueSaida extends Banco {

    public static List<EstoqueSaida> buscarTodos() throws Exception {
        List<EstoqueSaida> estoqueSaida;

        estoqueSaida = Dao.getInstance().getEm().createQuery("Select elemento From EstoqueSaida elemento").getResultList();

        return (estoqueSaida);
    }

    public static EstoqueSaida buscarEstoqueSaida(Integer id) throws Exception {
        EstoqueSaida estoqueSaida;

        estoqueSaida = Dao.getInstance().getEm().find(EstoqueSaida.class, id);

        return (estoqueSaida);
    }

    public static List<EstoqueSaida> buscarEstoqueSaida(EstoqueSaida estoqueSaida) throws Exception {
        Integer verificador = 0;
        List<EstoqueSaida> estoqueSaidas;
        String sql = "Select elemento From EstoqueSaida elemento Where 1=1 ";

        if (estoqueSaida.getDataSaida() != null) {
            sql += "and elemento.datasaida = :datasaida";
        } else {
            verificador++;
        }

        if (verificador == 1) {
            throw new Exception();
        } else {
            Query query = Dao.getInstance().getEm().createQuery(sql);

            if (estoqueSaida.getDataSaida() != null) {
                query.setParameter("datasaida", estoqueSaida.getDataSaida());
            }

            estoqueSaidas = query.getResultList();

            return (estoqueSaidas);
        }
    }

}
