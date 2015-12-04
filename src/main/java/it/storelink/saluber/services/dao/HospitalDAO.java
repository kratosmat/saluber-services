package it.storelink.saluber.services.dao;

import it.storelink.saluber.services.model.Hospital;
import org.springframework.stereotype.Repository;

/**
 * Created by kratos on 23/04/15.
 */
@Repository
public class HospitalDAO extends GenericDAOImpl<Hospital, Long> {

    public HospitalDAO() {
        this.entityClass = Hospital.class;
    }

}
