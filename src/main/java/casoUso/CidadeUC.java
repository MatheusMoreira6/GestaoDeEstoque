package casoUso;

import construtores.ConstrutorCidade;
import entidades.Cidade;
import java.util.List;
import persistencia.BancoCidade;

/**
 * @author Matheus
 * @author Gabriel
 */
public class CidadeUC {

    private CidadeUC() {

    }

    public static Cidade salvarCidade(ConstrutorCidade cidade) throws Exception {
        return ((Cidade) BancoCidade.salvar(cidade.construir()));
    }

    public static void removerCidade(Cidade cidade) throws Exception {
        BancoCidade.remover(cidade);
    }

    public static List<Cidade> buscarTodos() throws Exception {
        return (BancoCidade.buscarTodos());
    }

    public static Cidade buscarCidade(Integer id) throws Exception {
        return (BancoCidade.buscarCidade(id));
    }

    public static List<Cidade> buscarCidade(ConstrutorCidade cidade) throws Exception {
        return (BancoCidade.buscarCidade(cidade.construir()));
    }
}
