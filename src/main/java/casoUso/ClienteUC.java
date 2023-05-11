package casoUso;

import construtores.ConstrutorCliente;
import entidades.Cliente;
import java.util.List;
import persistencia.BancoCliente;

/**
 * @author Matheus
 * @author Gabriel
 */
public class ClienteUC {

    private ClienteUC() {

    }

    public static Cliente salvarCliente(ConstrutorCliente cliente) throws Exception {
        return ((Cliente) BancoCliente.salvar(cliente.construir()));
    }

    public static void removerCliente(Cliente cliente) throws Exception {
        BancoCliente.remover(cliente);
    }

    public static List<Cliente> buscarTodos() throws Exception {
        return (BancoCliente.buscarTodos());
    }

    public static Cliente buscarCliente(Integer id) throws Exception {
        return (BancoCliente.buscarCliente(id));
    }

    public static List<Cliente> buscarCliente(ConstrutorCliente cliente) throws Exception {
        return (BancoCliente.buscarCliente(cliente.construir()));
    }

}
