package it.storelink.saluber.services.dao;

import it.storelink.saluber.services.model.Booking;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by kratos on 23/04/15.
 */
@Repository
public class BookingDAO extends GenericDAOImpl<Booking, Long> {

    public BookingDAO() {
        this.entityClass = Booking.class;
    }

    public List<Booking> findByDoctor(Long id) throws DAOException {
        try {
            Query query = em.createQuery("from Booking " +
                    "where (doctor.id = :DOCTOR) order by id ASC");
            query.setParameter("DOCTOR", id);

            List<Booking> list = query.getResultList();
            return list;
        }
        catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public List<Booking> findByStation(Long id) throws DAOException {
        try {
            Query query = em.createQuery("from Booking " +
                    "where (station.id = :STATION) order by id ASC");
            query.setParameter("STATION", id);

            List<Booking> list = query.getResultList();
            return list;
        }
        catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public List<Booking> findByPatient(Long id) throws DAOException {
        try {
            Query query = em.createQuery("from Booking " +
                    "where (patient.id = :PATIENT) order by id ASC");
            query.setParameter("PATIENT", id);

            List<Booking> list = query.getResultList();
            return list;
        }
        catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public Booking findByDoctorSlot(Long id) throws DAOException {
        try {
            Query query = em.createQuery("from Booking where (doctorSlot = :SLOT)");
            query.setParameter("SLOT", id);

            List<Booking> list = query.getResultList();
            if(list.isEmpty()) return null;
            else return list.get(0);
        }
        catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public Booking findByStationSlot(Long id) throws DAOException {
        try {
            Query query = em.createQuery("from Booking where (stationSlot = :SLOT)");
            query.setParameter("SLOT", id);

            List<Booking> list = query.getResultList();
            if(list.isEmpty()) return null;
            else return list.get(0);
        }
        catch (Exception e) {
            throw new DAOException(e);
        }
    }
}
