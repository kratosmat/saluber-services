package it.storelink.saluber.services.dao;

import it.storelink.saluber.services.model.Day;
import org.springframework.stereotype.Repository;

/**
 * Created by kratos on 23/04/15.
 */
@Repository
public class DayDAO extends GenericDAOImpl<Day, Long> {

    public DayDAO() {
        this.entityClass = Day.class;
    }

}
