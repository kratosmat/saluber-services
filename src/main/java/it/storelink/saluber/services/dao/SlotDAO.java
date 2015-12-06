package it.storelink.saluber.services.dao;

import it.storelink.saluber.services.model.Slot;
import org.springframework.stereotype.Repository;

/**
 * Created by kratos on 23/04/15.
 */
@Repository
public class SlotDAO extends GenericDAOImpl<Slot, Long> {

    public SlotDAO() {
        this.entityClass = Slot.class;
    }

}
