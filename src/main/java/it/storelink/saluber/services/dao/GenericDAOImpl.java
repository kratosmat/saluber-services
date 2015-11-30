package it.storelink.saluber.services.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * User: kratos
 * Date: 26/04/15
 * Time: 9.19
 */

public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    @PersistenceContext
    protected EntityManager em;

    protected Class<T> entityClass;

    @Override
    public void save(T item) throws DAOException {
        try {

            em.persist(item);
            em.flush();

        }
        catch (Exception e) {
            throw new DAOException(e);
        }
        finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<T> list() throws DAOException {
        try {
            Query query = em.createQuery("from " + entityClass.getName());
            return query.getResultList();
        }
        catch (Exception e) {
            throw new DAOException(e);
        }
        finally {
            if (em != null) {
                em.close();
            }
        }

    }

    @Override
    public T find(ID id) throws DAOException {
        try {
            return em.find(entityClass, id);
        }
        catch (Exception e) {
            throw new DAOException(e);
        }
        finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void delete(ID id) throws DAOException {
        try {

            T entity = find(id);
            em.remove(entity);

        }
        catch (Exception e) {
            throw new DAOException(e);
        }
        finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void delete(T item) throws DAOException {
        try{
            em.remove(item);
        }catch (Exception e) {
            throw new DAOException(e);
        }
        finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
