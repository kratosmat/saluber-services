package it.storelink.saluber.services.dao;

import it.storelink.saluber.services.model.Booking;
import org.springframework.stereotype.Repository;

/**
 * Created by kratos on 23/04/15.
 */
@Repository
public class BookingDAO extends GenericDAOImpl<Booking, Long> {

    public BookingDAO() {
        this.entityClass = Booking.class;
    }

}
