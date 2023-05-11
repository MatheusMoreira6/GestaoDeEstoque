package casoUso;

import construtores.ConstrutorMarca;
import entidades.Marca;
import java.util.List;
import persistencia.BancoMarca;

/**
 * @author Matheus
 * @author Gabriel
 */
public class MarcaUC {

    private MarcaUC() {

    }

    public static Marca salvarMarca(ConstrutorMarca marca) throws Exception {
        return ((Marca) BancoMarca.salvar(marca.construir()));
    }

    public static void removerMarca(Marca marca) throws Exception {
        BancoMarca.remover(marca);
    }

    public static List<Marca> buscarTodos() throws Exception {
        return (BancoMarca.buscarTodos());
    }

    public static Marca buscarMarca(Integer id) throws Exception {
        return (BancoMarca.buscarMarca(id));
    }

    public static List<Marca> buscarMarca(ConstrutorMarca marca) throws Exception {
        return (BancoMarca.buscarMarca(marca.construir()));
    }

}
