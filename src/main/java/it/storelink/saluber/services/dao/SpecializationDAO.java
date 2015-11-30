package it.storelink.saluber.services.dao;

import it.storelink.saluber.services.model.Specialization;
import org.springframework.stereotype.Repository;

/**
 * Created by kratos on 23/04/15.
 */
@Repository
public class SpecializationDAO extends GenericDAOImpl<Specialization, Long> {

    public SpecializationDAO() {
        this.entityClass = Specialization.class;
    }

}
