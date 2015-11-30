package it.storelink.saluber.services.controller;

import it.storelink.saluber.services.dao.BookingDAO;
import it.storelink.saluber.services.dao.DoctorDAO;
import it.storelink.saluber.services.dao.PatientDAO;
import it.storelink.saluber.services.dao.StationDAO;
import it.storelink.saluber.services.model.*;
import it.storelink.saluber.services.service.BookingService;
import it.storelink.saluber.services.vo.BookingVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Service
@RequestMapping("/booking")
public class BookingRestController {


    private Log LOG = LogFactory.getLog(BookingRestController.class);

    @Autowired
    private BookingService bookingService;

    public void setBookingService(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @RequestMapping("/{bookingId}")
    public ResponseEntity<Booking> booking(@PathVariable Long bookingId) {
        ResponseEntity<Booking> entity;
        try {
            Booking booking = bookingService.findById(bookingId);
            if(booking==null) {
                entity = new ResponseEntity<Booking>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
            else {
                entity = new ResponseEntity<Booking>(booking, new HttpHeaders(), HttpStatus.FOUND);
            }
        }
        catch (Exception e) {
            entity = new ResponseEntity<Booking>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return entity;
    }

    @RequestMapping("/list")
    public ResponseEntity<List<Booking>> bookings() {

        ResponseEntity<List<Booking>> entity;
        try {
            List<Booking> bookings = bookingService.list();
            entity = new ResponseEntity<List<Booking>>(bookings, new HttpHeaders(), HttpStatus.FOUND);
        }
        catch (Exception e) {
            entity = new ResponseEntity<List<Booking>>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
            LOG.error(e);
        }

        return entity;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Long> saveBooking(@RequestBody BookingVO booking) {

        ResponseEntity<Long> entity;
        try {
            if(booking==null) {
                entity = new ResponseEntity<Long>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
            }
            else {
                Long id = bookingService.save(booking);
                entity = new ResponseEntity<Long>(id, new HttpHeaders(), HttpStatus.FOUND);
            }
        }
        catch (Exception e) {
            entity = new ResponseEntity<Long>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
            LOG.error(e);
        }
        return entity;
    }

    /*
    @RequestMapping(value = "/message", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Message> update(@RequestBody Message message) {

        if (message != null) {
            message.setEta(message.getEta() + 100);
        }

        // TODO: call persistence layer to update
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }
    */

}