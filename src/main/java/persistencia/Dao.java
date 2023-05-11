package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author Matheus
 * @author Gabriel
 */
public class Dao {

    private final EntityManagerFactory emf;
    private final EntityManager em;
    private final EntityTransaction et;
    private static Dao dao;

    private Dao() {
        emf = Persistence.createEntityManagerFactory("bancoGestaoDeEstoque");
        em = emf.createEntityManager();
        et = em.getTransaction();
    }

    public static Dao getInstance() {
        if (dao == null) {
            dao = new Dao();
        }

        return (dao);
    }

    public EntityManager getEm() {
        return (em);
    }

    public EntityTransaction getEt() {
        return (et);
    }
}
