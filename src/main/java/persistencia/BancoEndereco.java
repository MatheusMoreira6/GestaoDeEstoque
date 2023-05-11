package persistencia;

import entidades.Endereco;
import java.util.List;
import javax.persistence.Query;

/**
 * @author Matheus
 * @author Gabriel
 */
public class BancoEndereco extends Banco {

    public static List<Endereco> buscarTodos() throws Exception {
        List<Endereco> endereco;

        endereco = Dao.getInstance().getEm().createQuery("Select elemento From Endereco elemento").getResultList();

        return (endereco);
    }

    public static Endereco buscarEndereco(Integer id) throws Exception {
        Endereco endereco;

        endereco = Dao.getInstance().getEm().find(Endereco.class, id);

        return (endereco);
    }

    public static List<Endereco> buscarEndereco(Endereco endereco) throws Exception {
        Integer verificador = 0;
        List<Endereco> enderecos;
        String sql = "Select elemento From Endereco elemento Where 1=1 ";

        if (!endereco.getLogradouro().isBlank() || !endereco.getLogradouro().isEmpty()) {
            sql += "and elemento.logradouro = :logradouro ";
        } else {
            verificador++;
        }
        if (endereco.getNumero() != null) {
            sql += "and elemento.numero = :numero";
        } else {
            verificador++;
        }
        if (!endereco.getBairro().isBlank() || !endereco.getBairro().isEmpty()) {
            sql += "and elemento.bairro = :bairro";
        } else {
            verificador++;
        }

        if (verificador >= 1) {
            throw new Exception();
        } else {
            Query query = Dao.getInstance().getEm().createQuery(sql);

            if (!endereco.getLogradouro().isBlank() || !endereco.getLogradouro().isEmpty()) {
                query.setParameter("logradouro", endereco.getLogradouro());
            }

            if (endereco.getNumero() != null) {
                query.setParameter("numero", endereco.getNumero());
            }

            if (!endereco.getBairro().isBlank() || !endereco.getBairro().isEmpty()) {
                query.setParameter("bairro", endereco.getBairro());
            }

            enderecos = query.getResultList();

            return (enderecos);
        }
    }

}
