package it.storelink.saluber.services.service;

import it.storelink.saluber.services.dao.*;
import it.storelink.saluber.services.model.Month;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CalendarService {


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
    public Month month(Integer year, Integer month) throws BusinessException {
        try {

            return monthDAO.findByCriteria(year, month);
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }

}