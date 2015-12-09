package it.storelink.saluber.services.service;

import it.storelink.saluber.services.dao.*;
import it.storelink.saluber.services.helper.MonthHelper;
import it.storelink.saluber.services.model.*;
import it.storelink.saluber.services.vo.DayVO;
import it.storelink.saluber.services.vo.ExtendedUser;
import it.storelink.saluber.services.vo.MonthVO;
import it.storelink.saluber.services.vo.SlotVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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

    @Autowired
    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
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

    /*
    @Transactional
    public MonthVO readOrCreateForDoctor(Integer yearNumber, Integer monthNumber, String username) throws BusinessException {
        try {

            MonthDoctor monthDoctor = (MonthDoctor) monthDAO.findByCriteria4Doctor(yearNumber, monthNumber, username);
            if(monthDoctor==null) {
                monthDoctor = new MonthDoctor();
                Calendar calendar = new GregorianCalendar();
                calendar.set(Calendar.YEAR, yearNumber);
                //because calendar goes from 0 to 11
                calendar.set(Calendar.MONTH, monthNumber - 1);

                //because calendar goes from 0 to 30 max
                int maximum_day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + 1;

                //Create Month

                monthDoctor.setYear(yearNumber);
                monthDoctor.setMonth(monthNumber);
                monthDoctor.setUsername(username);

                monthDAO.save(monthDoctor);

                //List<Day> days = new LinkedList<Day>();
                for (int i = 1; i < maximum_day; i++) {
                    Day day = new Day();
                    day.setMonth(monthDoctor);
                    day.setNumber(i);
                    monthDoctor.getDays().add(day);
                    dayDAO.save(day);

                    //List<Slot> slots = new LinkedList<Slot>();
                    for (String slotLabel : slotLabels) {
                        Slot slot = new Slot();
                        slot.setDay(day);
                        slot.setStart(slotLabel);
                        slot.setSelected(false);

                        //FIXME: aumentare di 30 minuti la fine dello slot
                        slot.setEnd(slotLabel);
                        day.getSlots().add(slot);
                        slotDAO.save(slot);
                    }
                }
            }

            MonthVO monthVO = MonthHelper.entity2VO(monthDoctor);

            return monthVO;
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }


    @Transactional
    public MonthVO readOrCreateForStation(Integer yearNumber, Integer monthNumber, Long organization) throws BusinessException {
        try {

            MonthStation monthStation = (MonthStation) monthDAO.findByCriteria4Station(yearNumber, monthNumber, organization);
            if(monthStation==null) {
                monthStation = new MonthStation();
                Calendar calendar = new GregorianCalendar();
                calendar.set(Calendar.YEAR, yearNumber);
                //because calendar goes from 0 to 11
                calendar.set(Calendar.MONTH, monthNumber - 1);

                //because calendar goes from 0 to 30 max
                int maximum_day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + 1;

                //Create Month

                monthStation.setYear(yearNumber);
                monthStation.setMonth(monthNumber);
                //FIXME: usiamo l'id dell'organizzazione impropriamente
                monthStation.setStationId(organization);

                monthDAO.save(monthStation);

                //List<Day> days = new LinkedList<Day>();
                for (int i = 1; i < maximum_day; i++) {
                    Day day = new Day();
                    day.setMonth(monthStation);
                    day.setNumber(i);
                    monthStation.getDays().add(day);
                    dayDAO.save(day);

                    //List<Slot> slots = new LinkedList<Slot>();
                    for (String slotLabel : slotLabels) {
                        Slot slot = new Slot();
                        slot.setDay(day);
                        slot.setStart(slotLabel);
                        slot.setSelected(false);

                        //FIXME: aumentare di 30 minuti la fine dello slot
                        slot.setEnd(slotLabel);
                        day.getSlots().add(slot);
                        slotDAO.save(slot);
                    }
                }
            }

            //FIXME: occhio, stiamo usando la classe astratta
            MonthVO monthVO = MonthHelper.entity2VO(monthStation);

            return monthVO;
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }
    */

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
                        slot.setEnd(slotLabel);
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
    public MonthVO readAvailability(Integer yearNumber, Integer monthNumber, Long doctorId, Long stationId) throws BusinessException {
        try {

            Month monthStation = monthDAO.findByCriteria(yearNumber, monthNumber, "STATION", stationId);
            if(monthStation==null) return null;

            Month monthDoctor = monthDAO.findByCriteria(yearNumber, monthNumber, "DOCTOR", doctorId);
            if(monthDoctor==null) return null;


        }
        catch (Exception e) {

        }
        throw new NotImplementedException();
    }


    @Transactional
    public Long update(MonthVO monthVO) throws BusinessException {
        try {

            for(DayVO dayVO : monthVO.getDays()) {

                for(SlotVO slotVO : dayVO.getSlots()) {
                    Slot slot = slotDAO.find(slotVO.getId());
                    if(slot!=null) slot.setSelected(slotVO.getSelected());
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