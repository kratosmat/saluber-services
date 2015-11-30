package it.storelink.saluber.services.dao;

import it.storelink.saluber.services.model.Patient;
import org.springframework.stereotype.Repository;

/**
 * Created by kratos on 23/04/15.
 */
@Repository
public class PatientDAO extends GenericDAOImpl<Patient, Long> {

    public PatientDAO() {
        this.entityClass = Patient.class;
    }

}
