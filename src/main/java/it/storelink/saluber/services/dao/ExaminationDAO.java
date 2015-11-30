package it.storelink.saluber.services.dao;

import it.storelink.saluber.services.model.Examination;
import org.springframework.stereotype.Repository;

/**
 * Created by kratos on 23/04/15.
 */
@Repository
public class ExaminationDAO extends GenericDAOImpl<Examination, Long> {

    public ExaminationDAO() {
        this.entityClass = Examination.class;
    }

}
