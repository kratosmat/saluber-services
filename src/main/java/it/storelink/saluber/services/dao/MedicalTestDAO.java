package it.storelink.saluber.services.dao;

import it.storelink.saluber.services.model.MedicalTestType;
import it.storelink.saluber.services.model.Specialization;
import org.springframework.stereotype.Repository;

/**
 * Created by kratos on 23/04/15.
 */
@Repository
public class MedicalTestDAO extends GenericDAOImpl<MedicalTestType, Long> {

    public MedicalTestDAO() {
        this.entityClass = MedicalTestType.class;
    }

}
