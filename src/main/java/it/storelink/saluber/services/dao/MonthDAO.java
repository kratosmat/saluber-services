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
            Query query = em.createQuery("from Month where (year = :YEAR AND month<= :MONTH ) ");
            query.setParameter("YEAR", year.toString());
            query.setParameter("MONTH", month.toString());

            List<Month> list = query.getResultList();
            if(list.isEmpty()) return null;
            else return list.get(0);
        }
        catch (Exception e) {
            throw new DAOException(e);
        }
    }

}
