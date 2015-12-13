package it.storelink.saluber.services.service;

import it.storelink.saluber.services.dao.*;
import it.storelink.saluber.services.helper.MonthHelper;
import it.storelink.saluber.services.model.*;
import it.storelink.saluber.services.vo.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.regex.Pattern;

@Service
public class CalendarService {

    private static final String[] slotLabels = {"8.00", "8.30", "9.00", "9.30",
            "10.00", "10.30", "11.00", "11.30", "12.00", "12.30", "13.00",
            "15.00", "15.30", "16.00", "16.30", "17.00", "17.30", "18.00"};

    private Log LOG = LogFactory.getLog(CalendarService.class);

    @Autowired
    private MonthDAO monthDAO;

    public void setMonthDAO(MonthDAO monthDAO){
        this.monthDAO = monthDAO;
    }

    @Autowired
    private DayDAO dayDAO;

    public void setDayDAO(DayDAO dayDAO){
        this.dayDAO = dayDAO;
    }

    @Autowired
    private SlotDAO slotDAO;

    public void setSlotDAO(SlotDAO slotDAO){
        this.slotDAO = slotDAO;
    }

    /*
    @Autowired
    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
    }
    */

    @Autowired
    private BookingDAO bookingDAO;

    public void setBookingDAO(BookingDAO bookingDAO){
        this.bookingDAO = bookingDAO;
    }


    @Transactional
    public List<Month> months() throws BusinessException {
        try {
            return monthDAO.list();
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }

    @Transactional
    public Month read(Integer year, Integer month) throws BusinessException {
        try {

            return monthDAO.findByCriteria(year, month);
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }

    @Transactional
    public MonthVO readOrCreate(Integer yearNumber, Integer monthNumber, String type, Long organization) throws BusinessException {
        try {
            Month month = monthDAO.findByCriteria(yearNumber, monthNumber, type, organization);
            if(month==null) {
                month = new Month();
                Calendar calendar = new GregorianCalendar();
                calendar.set(Calendar.YEAR, yearNumber);
                //because calendar goes from 0 to 11
                calendar.set(Calendar.MONTH, monthNumber - 1);

                //because calendar goes from 0 to 30 max
                int maximum_day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + 1;

                //Create Month
                month.setYear(yearNumber);
                month.setMonth(monthNumber);
                month.setOrganizationId(organization);
                month.setType(type);

                monthDAO.save(month);

                //List<Day> days = new LinkedList<Day>();
                for (int i = 1; i < maximum_day; i++) {
                    Day day = new Day();
                    day.setMonth(month);
                    day.setNumber(i);
                    month.getDays().add(day);
                    dayDAO.save(day);

                    //List<Slot> slots = new LinkedList<Slot>();
                    for (String slotLabel : slotLabels) {


                        Slot slot = new Slot();
                        slot.setDay(day);
                        slot.setStart(slotLabel);
                        slot.setSelected(false);

                        //FIXME: aumentare di 30 minuti la fine dello slot
                        String[] splits = slotLabel.split(Pattern.quote("."));
                        calendar.set(Calendar.HOUR, Integer.valueOf(splits[0]));
                        calendar.set(Calendar.MINUTE, Integer.valueOf(splits[1]));
                        calendar.add(Calendar.MINUTE, 30);
                        slot.setEnd(calendar.get(Calendar.HOUR) + "." + calendar.get(Calendar.MINUTE));
                        day.getSlots().add(slot);

                        slotDAO.save(slot);
                    }
                }
            }

            //FIXME: occhio, stiamo usando la classe astratta
            MonthVO monthVO = MonthHelper.entity2VO(month);

            return monthVO;
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }


    @Transactional
    public MonthExtendedVO readAvailability(Integer yearNumber, Integer monthNumber, Long doctorId, Long stationId) throws BusinessException {
        try {

            Month monthStation = monthDAO.findByCriteria(yearNumber, monthNumber, "STATION", stationId);
            if(monthStation==null) {
                LOG.error("La station: " + stationId + " non possiede un calendario per anno: " + yearNumber + " mese: " + monthNumber);
                return null;
            }

            Month monthDoctor = monthDAO.findByCriteria(yearNumber, monthNumber, "DOCTOR", doctorId);
            if(monthDoctor==null) {
                LOG.error("Il doctor: " + doctorId + " non possiede un calendario per anno: " + yearNumber + " mese: " + monthNumber);
                return null;
            }

            List<Booking> bookingsByDoctor = bookingDAO.findByDoctor(doctorId);
            Map<Long, Long> bookingsByDoctorMap = new Hashtable<Long, Long>();
            for(Booking booking : bookingsByDoctor) {
                bookingsByDoctorMap.put(booking.getDoctorSlot(), doctorId);
            }

            List<Booking> bookingsByStation = bookingDAO.findByStation(stationId);
            Map<Long, Long> bookingsByStationMap = new Hashtable<Long, Long>();
            for(Booking booking : bookingsByStation) {
                bookingsByStationMap.put(booking.getStationSlot(), stationId);
            }

            //Creazione hash per station
            Map<String, Object[]> stationAvailability = new HashMap<String, Object[]>();
            for (Day day : monthStation.getDays()) {

                for (Slot slot : day.getSlots()) {
                    String key = day.getNumber() + "_" + slot.getStart();
                    if(bookingsByStationMap.containsKey(slot.getId())) {
                        stationAvailability.put(key, new Object[] {slot.getId(), false});
                    }
                    else {
                        stationAvailability.put(key, new Object[] {slot.getId(), slot.getSelected()});
                    }
                }
            }

            //Creazione hash per doctor
            Map<String, Object[]> doctorAvailability = new HashMap<String, Object[]>();
            for (Day day : monthDoctor.getDays()) {
                for (Slot slot : day.getSlots()) {
                    String key = day.getNumber() + "_" + slot.getStart();
                    if(bookingsByDoctorMap.containsKey(slot.getId())) {
                        doctorAvailability.put(key, new Object[] {slot.getId(), false});
                    }
                    else {
                        doctorAvailability.put(key, new Object[] {slot.getId(), slot.getSelected()});
                    }
                }
            }

            if(stationAvailability.size()!=doctorAvailability.size()) {
                LOG.error("Differenza dimensione calendari, station: " + stationAvailability.size() + " doctor: " + doctorAvailability.size());
                return null;
            }

            MonthExtendedVO monthAvailability = new MonthExtendedVO();
            monthAvailability.setType("AVAILABILITY");
            monthAvailability.setYear(yearNumber);
            monthAvailability.setMonth(monthNumber);

            Calendar calendar = new GregorianCalendar();
            calendar.set(Calendar.YEAR, yearNumber);
            //because calendar goes from 0 to 11
            calendar.set(Calendar.MONTH, monthNumber - 1);

            //because calendar goes from 0 to 30 max
            int maximum_day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + 1;

            for (int i = 1; i < maximum_day; i++) {
                DayExtendedVO day = new DayExtendedVO();
                day.setNumber(i);
                monthAvailability.getDays().add(day);

                for (String slotLabel : slotLabels) {
                    SlotExtendedVO slot = new SlotExtendedVO();

                    slot.setStationId((Long) stationAvailability.get(day.getNumber() + "_" + slotLabel)[0]);
                    slot.setDoctorId((Long) doctorAvailability.get(day.getNumber() + "_" + slotLabel)[0]);

                    slot.setStart(slotLabel);
                    slot.setDoctorAvailability((Boolean) stationAvailability.get(day.getNumber() + "_" + slotLabel)[1]);
                    slot.setStationAvailability((Boolean) doctorAvailability.get(day.getNumber() + "_" + slotLabel)[1]);

                    //FIXME: aumentare di 30 minuti la fine dello slot
                    String[] splits = slotLabel.split(Pattern.quote("."));
                    calendar.set(Calendar.HOUR, Integer.valueOf(splits[0]));
                    calendar.set(Calendar.MINUTE, Integer.valueOf(splits[1]));
                    calendar.add(Calendar.MINUTE, 30);
                    slot.setEnd(calendar.get(Calendar.HOUR) + "." + calendar.get(Calendar.MINUTE));
                    day.getSlots().add(slot);
                }
            }
            return monthAvailability;

        }
        catch (Exception e) {
            LOG.error(e);
            throw new BusinessException(e);
        }

    }


    @Transactional
    public Long update(MonthVO monthVO) throws BusinessException {
        try {

            for(DayVO dayVO : monthVO.getDays()) {

                for(SlotBaseVO slotVO : dayVO.getSlots()) {
                    Slot slot = slotDAO.find(slotVO.getId());
                    if(slot!=null) slot.setSelected(((SlotVO) slotVO).getSelected());
                    slotDAO.save(slot);
                }
            }
            return monthVO.getId();
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }


}