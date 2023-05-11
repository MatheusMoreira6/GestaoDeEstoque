package persistencia;

import entidades.Produto;
import java.util.List;
import javax.persistence.Query;

/**
 * @author Matheus
 * @author Gabriel
 */
public class BancoProduto extends Banco {

    public static List<Produto> buscarTodos() throws Exception {
        List<Produto> produto;

        produto = Dao.getInstance().getEm().createQuery("Select elemento From Produto elemento").getResultList();

        return (produto);
    }

    public static Produto buscarProduto(Integer id) throws Exception {
        Produto produto;

        produto = Dao.getInstance().getEm().find(Produto.class, id);

        return (produto);
    }

    public static List<Produto> buscarProduto(Produto produto) throws Exception {
        Integer verificador = 0;
        List<Produto> produtos;
        String sql = "Select elemento From Produto elemento Where 1=1 ";

        if (!produto.getNome().isBlank() || !produto.getNome().isEmpty()) {
            sql += "and elemento.nome = :nome ";
        } else {
            verificador++;
        }
        if (produto.getGarantia() != null) {
            sql += "and elemento.garantia = :garantia";
        } else {
            verificador++;
        }
        if (produto.getCategoria() != null) {
            sql += "and elemento.categoria = :categoria";
        } else {
            verificador++;
        }

        if (verificador == 3) {
            throw new Exception();
        } else {
            Query query = Dao.getInstance().getEm().createQuery(sql);

            if (!produto.getNome().isBlank() || !produto.getNome().isEmpty()) {
                query.setParameter("nome", produto.getNome());
            }

            if (produto.getGarantia() != null) {
                query.setParameter("garantia", produto.getGarantia());
            }

            if (produto.getCategoria() != null) {
                query.setParameter("categoria", produto.getCategoria().getId());
            }

            produtos = query.getResultList();

            return (produtos);
        }
    }

}
