package casoUso;

import construtores.ConstrutorEstoqueEntrada;
import entidades.EstoqueEntrada;
import java.util.List;
import persistencia.BancoEstoqueEntrada;

/**
 * @author Matheus
 * @author Gabriel
 */
public class EstoqueEntradaUC {

    private EstoqueEntradaUC() {

    }

    public static EstoqueEntrada salvarEstoqueEntrada(ConstrutorEstoqueEntrada estoqueEntrada) throws Exception {
        return ((EstoqueEntrada) BancoEstoqueEntrada.salvar(estoqueEntrada.construir()));
    }

    public static void removerEstoqueEntrada(EstoqueEntrada estoqueEntrada) throws Exception {
        BancoEstoqueEntrada.remover(estoqueEntrada);
    }

    public static List<EstoqueEntrada> buscarTodos() throws Exception {
        return (BancoEstoqueEntrada.buscarTodos());
    }

    public static EstoqueEntrada buscarEstoqueEntrada(Integer id) throws Exception {
        return (BancoEstoqueEntrada.buscarEstoqueEntrada(id));
    }

    public static List<EstoqueEntrada> buscarEstoqueEntrada(ConstrutorEstoqueEntrada estoqueEntrada) throws Exception {
        return (BancoEstoqueEntrada.buscarEstoqueEntrada(estoqueEntrada.construir()));
    }

}
