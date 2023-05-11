package casoUso;

import construtores.ConstrutorEstoqueSaida;
import entidades.EstoqueSaida;
import java.util.List;
import persistencia.BancoEstoqueSaida;

/**
 * @author Matheus
 * @author Gabriel
 */
public class EstoqueSaidaUC {

    private EstoqueSaidaUC() {

    }

    public static EstoqueSaida salvarEstoqueSaida(ConstrutorEstoqueSaida estoqueSaida) throws Exception {
        return ((EstoqueSaida) BancoEstoqueSaida.salvar(estoqueSaida.construir()));
    }

    public static void removerEstoqueSaida(EstoqueSaida estoqueSaida) throws Exception {
        BancoEstoqueSaida.remover(estoqueSaida);
    }

    public static List<EstoqueSaida> buscarTodos() throws Exception {
        return (BancoEstoqueSaida.buscarTodos());
    }

    public static EstoqueSaida buscarEstoqueSaida(Integer id) throws Exception {
        return (BancoEstoqueSaida.buscarEstoqueSaida(id));
    }

    public static List<EstoqueSaida> buscarEstoqueSaida(ConstrutorEstoqueSaida estoqueSaida) throws Exception {
        return (BancoEstoqueSaida.buscarEstoqueSaida(estoqueSaida.construir()));
    }

}
