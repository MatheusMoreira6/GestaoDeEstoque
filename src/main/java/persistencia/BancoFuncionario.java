package persistencia;

import entidades.Funcionario;
import java.util.List;
import javax.persistence.Query;

/**
 * @author Matheus
 * @author Gabriel
 */
public class BancoFuncionario extends Banco {

    public static List<Funcionario> buscarTodos() throws Exception {
        List<Funcionario> funcionarios;

        funcionarios = Dao.getInstance().getEm().createQuery("Select elemento From Funcionario elemento").getResultList();

        return (funcionarios);
    }

    public static Funcionario buscarFuncionario(Integer id) throws Exception {
        Funcionario funcionario;

        funcionario = Dao.getInstance().getEm().find(Funcionario.class, id);

        return (funcionario);
    }

    public static List<Funcionario> buscarFuncionario(Funcionario funcionario) throws Exception {
        Boolean verificador = false;
        List<Funcionario> funcionarios;
        String sql = "Select elemento From Funcionario elemento Where 1=1 ";

        if (funcionario.getLogin() != null) {
            sql += "and elemento.login = :login";

            if (funcionario.getSenha() != null) {
                sql += " and elemento.senha = :senha";
            }

            verificador = true;
        }

        if (verificador) {
            Query query = Dao.getInstance().getEm().createQuery(sql);

            if (funcionario.getLogin() != null) {
                query.setParameter("login", funcionario.getLogin());

                if (funcionario.getSenha() != null) {
                    query.setParameter("senha", funcionario.getSenha());
                }
            }

            funcionarios = query.getResultList();

            return (funcionarios);
        } else {
            throw new Exception();
        }
    }

}
