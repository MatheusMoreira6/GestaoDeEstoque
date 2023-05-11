package casoUso;

import construtores.ConstrutorFornecedor;
import entidades.Fornecedor;
import java.util.List;
import persistencia.BancoFornecedor;

/**
 * @author Matheus
 * @author Gabriel
 */
public class FornecedorUC {

    private FornecedorUC() {

    }

    public static Fornecedor salvarFornecedor(ConstrutorFornecedor fornecedor) throws Exception {
        return ((Fornecedor) BancoFornecedor.salvar(fornecedor.construir()));
    }

    public static void removerFornecedor(Fornecedor fornecedor) throws Exception {
        BancoFornecedor.remover(fornecedor);
    }

    public static List<Fornecedor> buscarTodos() throws Exception {
        return (BancoFornecedor.buscarTodos());
    }

    public static Fornecedor buscarFornecedor(Integer id) throws Exception {
        return (BancoFornecedor.buscarFornecedor(id));
    }

    public static List<Fornecedor> buscarFornecedor(ConstrutorFornecedor fornecedor) throws Exception {
        return (BancoFornecedor.buscarFornecedor(fornecedor.construir()));
    }

}
