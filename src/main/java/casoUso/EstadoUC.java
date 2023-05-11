package casoUso;

import construtores.ConstrutorEstado;
import entidades.Estado;
import java.util.List;
import persistencia.BancoEstado;

/**
 * @author Matheus
 * @author Gabriel
 */
public class EstadoUC {

    private EstadoUC() {

    }

    public static Estado salvarEstado(ConstrutorEstado estado) throws Exception {
        return ((Estado) BancoEstado.salvar(estado.construir()));
    }

    public static void removerEstado(Estado estado) throws Exception {
        BancoEstado.remover(estado);
    }

    public static List<Estado> buscarTodos() throws Exception {
        return (BancoEstado.buscarTodos());
    }

    public static Estado buscarEstado(Integer id) throws Exception {
        return (BancoEstado.buscarEstado(id));
    }

    public static List<Estado> buscarEstado(ConstrutorEstado estado) throws Exception {
        return (BancoEstado.buscarEstado(estado.construir()));
    }

}
