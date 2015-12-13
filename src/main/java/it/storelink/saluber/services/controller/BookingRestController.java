package it.storelink.saluber.services.controller;

import it.storelink.saluber.services.service.BookingService;
import it.storelink.saluber.services.vo.BookingVO;
import it.storelink.saluber.services.vo.ExtendedUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Service
@RequestMapping("/api/booking")
public class BookingRestController {


    private Log LOG = LogFactory.getLog(BookingRestController.class);

    @Autowired
    private BookingService bookingService;

    public void setBookingService(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @RequestMapping("/{bookingId}")
    public ResponseEntity<BookingVO> booking(@PathVariable Long bookingId) {
        ResponseEntity<BookingVO> entity;
        try {
            BookingVO booking = bookingService.findById(bookingId);
            if(booking==null) {
                entity = new ResponseEntity<BookingVO>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
            else {
                entity = new ResponseEntity<BookingVO>(booking, new HttpHeaders(), HttpStatus.OK);
            }
        }
        catch (Exception e) {
            LOG.error(e);
            entity = new ResponseEntity<BookingVO>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return entity;
    }

    @RequestMapping("/list")
    public ResponseEntity<List<BookingVO>> bookings() {

        ResponseEntity<List<BookingVO>> entity;
        List<BookingVO> bookings = null;
        try {
            ExtendedUser user = (ExtendedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(user.hasAuthority("doctor")) {
                bookings = bookingService.findByDoctor(user.getOrganization());
            }
            else if(user.hasAuthority("station_manager")) {
                bookings = bookingService.findByStation(user.getOrganization());
            }
            else if(user.hasAuthority("patient")) {
                bookings = bookingService.findByPatient(user.getOrganization());
            }

            if(bookings==null) throw new NullPointerException();
            entity = new ResponseEntity<List<BookingVO>>(bookings, new HttpHeaders(), HttpStatus.OK);
        }
        catch (Exception e) {
            entity = new ResponseEntity<List<BookingVO>>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
            LOG.error(e);
        }

        return entity;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> saveBooking(@RequestBody BookingVO booking) {

        try {
            if(booking==null) throw new Exception("La prenotazione è nulla");
            Long id = bookingService.save(booking);
            return new ResponseEntity<Long>(id, new HttpHeaders(), HttpStatus.OK);
        }
        catch (Exception e) {
            LOG.error(e);
            return new ResponseEntity<String>(e.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}