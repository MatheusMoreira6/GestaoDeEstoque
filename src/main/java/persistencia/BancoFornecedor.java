package persistencia;

import entidades.Fornecedor;
import java.util.List;
import javax.persistence.Query;

/**
 * @author Matheus
 * @author mathe
 */
public class BancoFornecedor extends Banco {

    public static List<Fornecedor> buscarTodos() throws Exception {
        List<Fornecedor> fornecedor;

        fornecedor = Dao.getInstance().getEm().createQuery("Select elemento From Fornecedor elemento").getResultList();

        return (fornecedor);
    }

    public static Fornecedor buscarFornecedor(Integer id) throws Exception {
        Fornecedor fornecedor;

        fornecedor = Dao.getInstance().getEm().find(Fornecedor.class, id);

        return (fornecedor);
    }

    public static List<Fornecedor> buscarFornecedor(Fornecedor fornecedor) throws Exception {
        Integer verificador = 0;
        List<Fornecedor> fornecedores;
        String sql = "Select elemento From Fornecedor elemento Where 1=1 ";

        if (!fornecedor.getNomeFantasia().isBlank() || !fornecedor.getNomeFantasia().isEmpty()) {
            sql += "and elemento.nomefantasia = :nomefantasia ";
        } else {
            verificador++;
        }
        if (!fornecedor.getCnpj().isBlank() || !fornecedor.getCnpj().isEmpty()) {
            sql += "and elemento.cnpj = :cnpj";
        } else {
            verificador++;
        }

        if (verificador == 2) {
            throw new Exception();
        } else {
            Query query = Dao.getInstance().getEm().createQuery(sql);

            if (!fornecedor.getNomeFantasia().isBlank() || !fornecedor.getNomeFantasia().isEmpty()) {
                query.setParameter("nomefantasia", fornecedor.getNomeFantasia());
            }

            if (!fornecedor.getCnpj().isBlank() || !fornecedor.getCnpj().isEmpty()) {
                query.setParameter("cnpj", fornecedor.getCnpj());
            }

            fornecedores = query.getResultList();

            return (fornecedores);
        }
    }

}
