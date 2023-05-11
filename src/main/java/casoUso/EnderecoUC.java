package casoUso;

import construtores.ConstrutorEndereco;
import entidades.Endereco;
import java.util.List;
import persistencia.BancoEndereco;

/**
 * @author Matheus
 * @author Gabriel
 */
public class EnderecoUC {

    private EnderecoUC() {

    }

    public static Endereco salvarEndereco(ConstrutorEndereco endereco) throws Exception {
        return ((Endereco) BancoEndereco.salvar(endereco.construir()));
    }

    public static void removerEndereco(Endereco endereco) throws Exception {
        BancoEndereco.remover(endereco);
    }

    public static List<Endereco> buscarTodos() throws Exception {
        return (BancoEndereco.buscarTodos());
    }

    public static Endereco buscarEndereco(Integer id) throws Exception {
        return (BancoEndereco.buscarEndereco(id));
    }

    public static List<Endereco> buscarEndereco(ConstrutorEndereco endereco) throws Exception {
        return (BancoEndereco.buscarEndereco(endereco.construir()));
    }

}
