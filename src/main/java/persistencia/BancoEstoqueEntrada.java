package persistencia;

import entidades.EstoqueEntrada;
import java.util.List;
import javax.persistence.Query;

/**
 * @author Matheus
 * @author Gabriel
 */
public class BancoEstoqueEntrada extends Banco {

    public static List<EstoqueEntrada> buscarTodos() throws Exception {
        List<EstoqueEntrada> estoqueEntrada;

        estoqueEntrada = Dao.getInstance().getEm().createQuery("Select elemento From EstoqueEntrada elemento").getResultList();

        return (estoqueEntrada);
    }

    public static EstoqueEntrada buscarEstoqueEntrada(Integer id) throws Exception {
        EstoqueEntrada estoqueEntrada;

        estoqueEntrada = Dao.getInstance().getEm().find(EstoqueEntrada.class, id);

        return (estoqueEntrada);
    }

    public static List<EstoqueEntrada> buscarEstoqueEntrada(EstoqueEntrada dadosEntrada) throws Exception {
        Boolean verificador = false;
        List<EstoqueEntrada> estoqueEntradas;
        String sql = "Select elemento From EstoqueEntrada elemento Where 1=1 ";

        if (dadosEntrada.getNotaFiscal() != null) {
            sql += "and elemento.notafiscal = :notafiscal";

            verificador = true;
        }

        if (verificador) {
            Query query = Dao.getInstance().getEm().createQuery(sql);

            query.setParameter("notafiscal", dadosEntrada.getNotaFiscal());

            estoqueEntradas = query.getResultList();

            return (estoqueEntradas);
        } else {
            throw new Exception();
        }
    }

}
