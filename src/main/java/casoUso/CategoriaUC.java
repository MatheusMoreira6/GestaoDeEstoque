package casoUso;

import construtores.ConstrutorCategoria;
import entidades.Categoria;
import java.util.List;
import persistencia.BancoCategoria;

/**
 * @author Matheus
 * @author Gabriel
 */
public class CategoriaUC {

    private CategoriaUC() {

    }

    public static Categoria salvarCategoria(ConstrutorCategoria categoria) throws Exception {
        return ((Categoria) BancoCategoria.salvar(categoria.construir()));
    }

    public static void removerCategoria(Categoria categoria) throws Exception {
        BancoCategoria.remover(categoria);
    }

    public static List<Categoria> buscarTodos() throws Exception {
        return (BancoCategoria.buscarTodos());
    }

    public static Categoria buscarCategoria(Integer id) throws Exception {
        return (BancoCategoria.buscarCategoria(id));
    }

    public static List<Categoria> buscarCategoria(ConstrutorCategoria categoria) throws Exception {
        return (BancoCategoria.buscarCategoria(categoria.construir()));
    }

}
