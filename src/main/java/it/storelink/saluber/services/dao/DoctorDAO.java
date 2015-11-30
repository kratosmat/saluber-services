package it.storelink.saluber.services.dao;

import it.storelink.saluber.services.model.Doctor;
import org.springframework.stereotype.Repository;

/**
 * Created by kratos on 23/04/15.
 */
@Repository
public class DoctorDAO extends GenericDAOImpl<Doctor, Long> {

    public DoctorDAO() {
        this.entityClass = Doctor.class;
    }

}
