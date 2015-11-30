package it.storelink.saluber.services.dao;

import it.storelink.saluber.services.model.Station;
import org.springframework.stereotype.Repository;

/**
 * Created by kratos on 23/04/15.
 */
@Repository
public class StationDAO extends GenericDAOImpl<Station, Long> {

    public StationDAO() {
        this.entityClass = Station.class;
    }

}
