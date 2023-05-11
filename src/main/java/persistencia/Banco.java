package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * @author Matheus
 * @author Gabriel
 */
public abstract class Banco {

    protected Banco() {

    }

    private static EntityTransaction getEt() {
        return (Dao.getInstance().getEt());
    }

    protected static EntityManager getEm() {
        return (Dao.getInstance().getEm());
    }

    public static Object salvar(Object objeto) throws Exception {
        Object salvo = null;

        try {
            getEt().begin();

            salvo = getEm().merge(objeto);

            getEt().commit();

            return (salvo);
        } catch (Exception excecao) {
            getEt().rollback();

            throw excecao;
        }
    }

    public static void remover(Object objeto) throws Exception {
        try {
            getEt().begin();

            getEm().remove(objeto);

            getEt().commit();
        } catch (Exception excecao) {
            getEt().rollback();

            throw excecao;
        }
    }

}
