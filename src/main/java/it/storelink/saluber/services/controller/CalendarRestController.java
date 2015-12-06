package it.storelink.saluber.services.controller;

import it.storelink.saluber.services.model.Month;
import it.storelink.saluber.services.service.CalendarService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calendar")
public class CalendarRestController {

    private Log LOG = LogFactory.getLog(CalendarRestController.class);

    @Autowired
    private CalendarService calendarService;

    public void setCalendarService(CalendarService calendarService){
        this.calendarService = calendarService;
    }

    @RequestMapping("/month/{yearNumber}/{monthNumber}")
    public ResponseEntity<Month> month(@PathVariable Integer yearNumber, @PathVariable Integer monthNumber) {
        ResponseEntity<Month> entity = null;
        try {

            Month month = calendarService.month(yearNumber, monthNumber);

            if(month==null) {
                entity = new ResponseEntity<Month>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
            else {
                entity = new ResponseEntity<Month>(month, new HttpHeaders(), HttpStatus.OK);
            }
        }
        catch (Exception e) {
            entity = new ResponseEntity<Month>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
            LOG.error(e);
        }
        return entity;
    }


}