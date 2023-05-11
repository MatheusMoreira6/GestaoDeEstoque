package construtores;

import entidades.CargoFuncionario;

/**
 * @author Matheus
 * @author Gabriel
 */
public class ConstrutorCargoFuncionario {

    private final String cargo;
    private final Boolean funcoesAdministrador;
    private final Integer id;

    public ConstrutorCargoFuncionario(String cargo, Boolean funcoesAdministrador, Integer id) {
        this.cargo = cargo;
        this.funcoesAdministrador = funcoesAdministrador;
        this.id = id;
    }

    private ConstrutorCargoFuncionario validar() throws Exception {
        if (id == null) {
            throw new Exception();
        } else if (cargo.isBlank() || cargo.isEmpty()) {
            throw new Exception();
        } else if (funcoesAdministrador == null) {
            throw new Exception();
        }

        return (this);
    }

    public CargoFuncionario construir() throws Exception {
        validar();

        CargoFuncionario cargoFuncionario = new CargoFuncionario();
        cargoFuncionario.setId(id);
        cargoFuncionario.setCargo(cargo);
        cargoFuncionario.setFuncoesAdministrador(funcoesAdministrador);

        return (cargoFuncionario);
    }
}
