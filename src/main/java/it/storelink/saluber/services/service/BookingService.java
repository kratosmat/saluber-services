package it.storelink.saluber.services.service;

import it.storelink.saluber.services.dao.*;
import it.storelink.saluber.services.helper.BookingHelper;
import it.storelink.saluber.services.model.*;
import it.storelink.saluber.services.vo.BookingVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
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

    @Autowired
    private SlotDAO slotDAO;

    public void setSlotDAO(SlotDAO slotDAO){
        this.slotDAO = slotDAO;
    }

    @Transactional
    public List<BookingVO> list() throws BusinessException {
        List<BookingVO> bookingVOs = new LinkedList<BookingVO>();
        try {
            List<Booking> bookings = bookingDAO.list();
            for (Booking booking : bookings) {
                BookingVO bookingVO = BookingHelper.entity2VO(booking);
                bookingVOs.add(bookingVO);
            }
            return bookingVOs;
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }

    @Transactional
    public BookingVO findById(Long id) throws BusinessException {
        try {
            Booking booking = bookingDAO.find(id);
            if(booking!=null) return BookingHelper.entity2VO(booking);
            else return null;
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }

    @Transactional
    public List<BookingVO> findByDoctor(Long id) throws BusinessException {
        try {
            List<Booking> bookings = bookingDAO.findByDoctor(id);
            return BookingHelper.entity2VO(bookings);
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }

    @Transactional
    public List<BookingVO> findByStation(Long id) throws BusinessException {
        try {
            List<Booking> bookings = bookingDAO.findByStation(id);
            return BookingHelper.entity2VO(bookings);
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }

    @Transactional
    public List<BookingVO> findByPatient(Long id) throws BusinessException {
        try {
            List<Booking> bookings = bookingDAO.findByPatient(id);
            return BookingHelper.entity2VO(bookings);
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
            //verificare che realmente gli slot siano disponibili
            Slot doctorSlot = slotDAO.find(booking.getDoctorSlot());
            if(!doctorSlot.getSelected()) {
                throw new BusinessException("Lo slot del dottore con id: " + doctorSlot.getId() + " non è disponinile");
            }
            if(bookingDAO.findByDoctorSlot(booking.getDoctorSlot())!=null) {
                throw new BusinessException("Il dottore ha già una prenotazione per lo slot id: " + doctorSlot.getId());
            }

            Slot stationSlot = slotDAO.find(booking.getStationSlot());
            if(!stationSlot.getSelected()) {
                throw new BusinessException("Lo slot della station con id: " + stationSlot.getId() + " non è disponinile");
            }
            if(bookingDAO.findByStationSlot(booking.getStationSlot())!=null) {
                throw new BusinessException("La station ha già una prenotazione per lo slot id: " + stationSlot.getId());
            }

            entBooking = BookingHelper.vo2Entity(booking);

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