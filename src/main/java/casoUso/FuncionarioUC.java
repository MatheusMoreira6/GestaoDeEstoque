package casoUso;

import construtores.ConstrutorFuncionario;
import entidades.Funcionario;
import java.util.List;
import persistencia.BancoFuncionario;

/**
 * @author Matheus
 * @author Gabriel
 */
public class FuncionarioUC {

    private FuncionarioUC() {

    }

    public static Funcionario salvarFuncionario(ConstrutorFuncionario funcionario) throws Exception {
        return ((Funcionario) BancoFuncionario.salvar(funcionario.construir()));
    }

    public static void removerFuncionario(Funcionario funcionario) throws Exception {
        BancoFuncionario.remover(funcionario);
    }

    public static List<Funcionario> buscarTodos() throws Exception {
        return (BancoFuncionario.buscarTodos());
    }

    public static Funcionario buscarFuncionario(Integer id) throws Exception {
        return (BancoFuncionario.buscarFuncionario(id));
    }

    public static List<Funcionario> buscarFuncionario(ConstrutorFuncionario funcionario) throws Exception {
        return (BancoFuncionario.buscarFuncionario(funcionario.construir()));
    }

}
