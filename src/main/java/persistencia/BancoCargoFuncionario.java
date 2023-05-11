package persistencia;

import entidades.CargoFuncionario;
import java.util.List;
import javax.persistence.Query;

/**
 * @author Matheus
 * @author Gabriel
 */
public class BancoCargoFuncionario extends Banco {

    public static List<CargoFuncionario> buscarTodos() throws Exception {
        List<CargoFuncionario> cargo;

        cargo = Dao.getInstance().getEm().createQuery("Select elemento From CargoFuncionario elemento").getResultList();

        return (cargo);
    }

    public static CargoFuncionario buscarCargo(Integer id) throws Exception {
        CargoFuncionario cargo;

        cargo = Dao.getInstance().getEm().find(CargoFuncionario.class, id);

        return (cargo);
    }

    public static List<CargoFuncionario> buscarCargo(CargoFuncionario cargo) throws Exception {
        Integer verificador = 0;
        List<CargoFuncionario> cargos;
        String sql = "Select elemento From CargoFuncionario elemento Where 1=1 ";

        if (!cargo.getCargo().isBlank() || !cargo.getCargo().isEmpty()) {
            sql += "and elemento.cargo = :cargo";
        } else {
            verificador++;
        }

        if (verificador == 1) {
            throw new Exception();
        } else {
            Query query = Dao.getInstance().getEm().createQuery(sql);

            query.setParameter("cargo", cargo.getCargo());

            cargos = query.getResultList();

            return (cargos);
        }
    }

}
