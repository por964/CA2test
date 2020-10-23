package facades;

import dto.CityInfosDTO;
import entities.CityInfo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author claes
 */
public class CityInfoFacade {
    
    private static CityInfoFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CityInfoFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CityInfoFacade getCityInfoFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CityInfoFacade();
        }
        return instance;
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public long getCityCount() {
        EntityManager em = getEntityManager();
        try {
            long cityCount = (long) em.createQuery("SELECT COUNT(c) FROM CityInfo c").getSingleResult();
            return cityCount;
        } finally {
            em.close();
        }

    }
    public CityInfosDTO getAllCities() {
        EntityManager em = getEntityManager();
        try {
            return new CityInfosDTO(em.createNamedQuery("CityInfo.getAllRows").getResultList());
        } finally {
            em.close();
        }
    }
}
