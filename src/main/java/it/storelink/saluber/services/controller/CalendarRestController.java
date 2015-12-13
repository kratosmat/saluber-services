package it.storelink.saluber.services.controller;

import it.storelink.saluber.services.service.CalendarService;
import it.storelink.saluber.services.vo.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<MonthVO> month(@PathVariable Integer yearNumber, @PathVariable Integer monthNumber) {
        ResponseEntity<MonthVO> entity;
        try {
            ExtendedUser user = (ExtendedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            MonthVO month = null;
            if(user.hasAuthority("doctor")) {
                month = calendarService.readOrCreate(yearNumber, monthNumber, "DOCTOR", user.getOrganization());
            }
            if(user.hasAuthority("station_manager")) {
                month = calendarService.readOrCreate(yearNumber, monthNumber, "STATION", user.getOrganization());
            }

            if(month==null) {
                entity = new ResponseEntity<MonthVO>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
            else {
                entity = new ResponseEntity<MonthVO>(month, new HttpHeaders(), HttpStatus.OK);
            }
        }
        catch (Exception e) {
            entity = new ResponseEntity<MonthVO>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
            LOG.error(e);
        }
        return entity;
    }

    @RequestMapping(value = "/save_month", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Long> saveMonth(@RequestBody MonthVO monthVO) {
        ResponseEntity<Long> entity;
        Long month_id;
        try {
            ExtendedUser user = (ExtendedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if(!user.hasAuthority("doctor") && !user.hasAuthority("station_manager"))
                return new ResponseEntity<Long>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);

            month_id = calendarService.update(monthVO);

            if(month_id==null) {
                entity = new ResponseEntity<Long>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
            else {
                entity = new ResponseEntity<Long>(month_id, new HttpHeaders(), HttpStatus.OK);
            }
        }
        catch (Exception e) {
            entity = new ResponseEntity<Long>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
            LOG.error(e);
        }
        return entity;
    }

    @RequestMapping("/month_availability/{yearNumber}/{monthNumber}/{doctorId}/{stationId}")
    public ResponseEntity<?> monthAvailability(
            @PathVariable Integer yearNumber,
            @PathVariable Integer monthNumber,
            @PathVariable Long doctorId,
            @PathVariable Long stationId) {

        try {
            ExtendedUser user = (ExtendedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(user.hasAuthority("patient")) {
                MonthExtendedVO month = calendarService.readAvailability(yearNumber, monthNumber, doctorId, stationId);
                return new ResponseEntity<MonthExtendedVO>(month, new HttpHeaders(), HttpStatus.OK);
            }
            else return new ResponseEntity<String>("", new HttpHeaders(), HttpStatus.FORBIDDEN);

        }
        catch (Exception e) {
            LOG.error(e);
            return new ResponseEntity<String>(e.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    @RequestMapping(value = "/save_month_station", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Long> saveMonthStation(@RequestBody MonthVO monthVO) {
        ResponseEntity<Long> entity;
        Long month_id;
        try {
            ExtendedUser user = (ExtendedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if(!user.hasAuthority("station_manager")) return new ResponseEntity<Long>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);

            month_id = calendarService.update(monthVO);

            if(month_id==null) {
                entity = new ResponseEntity<Long>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
            else {
                entity = new ResponseEntity<Long>(month_id, new HttpHeaders(), HttpStatus.OK);
            }
        }
        catch (Exception e) {
            entity = new ResponseEntity<Long>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
            LOG.error(e);
        }
        return entity;
    }
    */

}