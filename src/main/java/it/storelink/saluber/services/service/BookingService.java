package it.storelink.saluber.services.service;

import it.storelink.saluber.services.dao.*;
import it.storelink.saluber.services.model.*;
import it.storelink.saluber.services.vo.BookingVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingService {


    private Log LOG = LogFactory.getLog(BookingService.class);

    @Autowired
    private BookingDAO bookingDAO;

    public void setBookingDAO(BookingDAO bookingDAO){
        this.bookingDAO = bookingDAO;
    }

    @Autowired
    private DoctorDAO doctorDAO;

    public void setDoctorDAO(DoctorDAO doctorDAO){
        this.doctorDAO = doctorDAO;
    }

    @Autowired
    private PatientDAO patientDAO;

    public void setPatientDAO(PatientDAO patientDAO){
        this.patientDAO = patientDAO;
    }

    @Autowired
    private StationDAO stationDAO;

    public void setStationDAO(StationDAO stationDAO){
        this.stationDAO = stationDAO;
    }

    @Autowired
    private SpecializationDAO specializationDAO;

    public void setSpecializationDAO(SpecializationDAO specializationDAO){
        this.specializationDAO = specializationDAO;
    }

    @Transactional
    public List<Booking> list() throws BusinessException {
        try {
            return bookingDAO.list();
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }

    @Transactional
    public Booking findById(Long id) throws BusinessException {
        try {
            return bookingDAO.find(id);
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }

    @Transactional
    public Long save(BookingVO booking) throws BusinessException {
        Booking entBooking;
        try {
            entBooking = new Booking();
            //entBooking.setId(booking.getId());
            entBooking.setDateStart(booking.getDateStart());

            Doctor doctor = doctorDAO.find(booking.getDoctorId());
            entBooking.setDoctor(doctor);

            Patient patient = patientDAO.find(booking.getPatientId());
            entBooking.setPatient(patient);

            Station station = stationDAO.find(booking.getStationId());
            entBooking.setStation(station);

            Specialization specialization = specializationDAO.find(booking.getSpecializationId());
            entBooking.setSpecialization(specialization);


            bookingDAO.save(entBooking);
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
        return booking.getId();
    }

}