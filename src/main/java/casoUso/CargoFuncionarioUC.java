package casoUso;

import construtores.ConstrutorCargoFuncionario;
import entidades.CargoFuncionario;
import java.util.List;
import persistencia.BancoCargoFuncionario;

/**
 * @author Matheus
 * @author Gabriel
 */
public class CargoFuncionarioUC {

    private CargoFuncionarioUC() {

    }

    public static CargoFuncionario salvarCargoFuncionario(ConstrutorCargoFuncionario cargo) throws Exception {
        return ((CargoFuncionario) BancoCargoFuncionario.salvar(cargo.construir()));
    }

    public static void removerCargoFuncionario(CargoFuncionario cargo) throws Exception {
        BancoCargoFuncionario.remover(cargo);
    }

    public static List<CargoFuncionario> buscarTodos() throws Exception {
        return (BancoCargoFuncionario.buscarTodos());
    }

    public static CargoFuncionario buscarCargo(Integer id) throws Exception {
        return (BancoCargoFuncionario.buscarCargo(id));
    }

    public static List<CargoFuncionario> buscarCargo(ConstrutorCargoFuncionario cargo) throws Exception {
        return (BancoCargoFuncionario.buscarCargo(cargo.construir()));
    }

}
