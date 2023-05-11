package persistencia;

import entidades.Cliente;
import java.util.List;
import javax.persistence.Query;

/**
 * @author Matheus
 * @author Gabriel
 */
public class BancoCliente extends Banco {

    public static List<Cliente> buscarTodos() throws Exception {
        List<Cliente> cliente;

        cliente = Dao.getInstance().getEm().createQuery("Select elemento From Cliente elemento").getResultList();

        return (cliente);
    }

    public static Cliente buscarCliente(Integer id) throws Exception {
        Cliente cliente;

        cliente = Dao.getInstance().getEm().find(Cliente.class, id);

        return (cliente);
    }

    public static List<Cliente> buscarCliente(Cliente cliente) throws Exception {
        Integer verificador = 0;
        List<Cliente> clientes;
        String sql = "Select elemento From Cliente elemento Where 1=1 ";

        if (!cliente.getNome().isBlank() || !cliente.getNome().isEmpty()) {
            sql += "and elemento.nome = :nome ";
        } else {
            verificador++;
        }

        if (!cliente.getCpf().isBlank() || !cliente.getCpf().isEmpty()) {
            sql += "and elemento.cpf = :cpf";
        } else {
            verificador++;
        }

        if (verificador >= 1) {
            throw new Exception();
        } else {
            Query query = Dao.getInstance().getEm().createQuery(sql);

            if (!cliente.getNome().isBlank() || !cliente.getNome().isEmpty()) {
                query.setParameter("nome", cliente.getNome());
            }
            if (!cliente.getCpf().isBlank() || !cliente.getCpf().isEmpty()) {
                query.setParameter("cpf", cliente.getCpf());
            }

            clientes = query.getResultList();

            return (clientes);
        }
    }

}
