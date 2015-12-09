package it.storelink.saluber.services.dao;

import it.storelink.saluber.services.model.Month;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by kratos on 23/04/15.
 */
@Repository
public class MonthDAO extends GenericDAOImpl<Month, Long> {

    public MonthDAO() {
        this.entityClass = Month.class;
    }

    public Month findByCriteria(Integer year, Integer month) throws DAOException {
        try {
            Query query = em.createQuery("from Month " +
                    "where (year = :YEAR AND month = :MONTH ) " +
                    "order by id ASC");
            query.setParameter("YEAR", year);
            query.setParameter("MONTH", month);

            List<Month> list = query.getResultList();
            if(list.isEmpty()) return null;
            else return list.get(0);
        }
        catch (Exception e) {
            throw new DAOException(e);
        }
    }

    /*
    public MonthDoctor findByCriteria4Doctor(Integer year, Integer month, String username) throws DAOException {
        try {
            Query query = em.createQuery("from MonthDoctor " +
                    "where (year = :YEAR AND month = :MONTH AND username = :USERNAME) " +
                    "order by id ASC");
            query.setParameter("YEAR", year);
            query.setParameter("MONTH", month);
            query.setParameter("USERNAME", username);

            List<Month> list = query.getResultList();
            if(list.isEmpty()) return null;
            else return (MonthDoctor) list.get(0);
        }
        catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public MonthStation findByCriteria4Station(Integer year, Integer month, Long stationId) throws DAOException {
        try {
            Query query = em.createQuery("from MonthStation " +
                    "where (year = :YEAR AND month = :MONTH AND stationId = :STATION) " +
                    "order by id ASC");
            query.setParameter("YEAR", year);
            query.setParameter("MONTH", month);
            query.setParameter("STATION", stationId);

            List<Month> list = query.getResultList();
            if(list.isEmpty()) return null;
            else return (MonthStation) list.get(0);
        }
        catch (Exception e) {
            throw new DAOException(e);
        }
    }
    */

    public Month findByCriteria(Integer year, Integer month, String type, Long organizationId) throws DAOException {
        try {
            Query query = em.createQuery("from Month " +
                    "where (year = :YEAR AND month = :MONTH AND organizationId = :ORGANIZATION AND type = :TYPE) " +
                    "order by id ASC");
            query.setParameter("YEAR", year);
            query.setParameter("MONTH", month);
            query.setParameter("ORGANIZATION", organizationId);
            query.setParameter("TYPE", type);

            List<Month> list = query.getResultList();
            if(list.isEmpty()) return null;
            else return list.get(0);
        }
        catch (Exception e) {
            throw new DAOException(e);
        }
    }

}
