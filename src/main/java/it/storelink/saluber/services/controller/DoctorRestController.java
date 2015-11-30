package it.storelink.saluber.services.controller;

import it.storelink.saluber.services.dao.DoctorDAO;
import it.storelink.saluber.services.model.Doctor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorRestController {

    @Autowired
    private DoctorDAO doctorDAO;

    private Log LOG = LogFactory.getLog(DoctorRestController.class);

    public void setDoctorDAO(DoctorDAO doctorDAO){
        this.doctorDAO = doctorDAO;
    }

    @RequestMapping("/{doctorId}")
    @Transactional
    public ResponseEntity<Doctor> doctor(@PathVariable Long doctorId) {
        ResponseEntity<Doctor> entity = null;
        try {
            Doctor doctor = doctorDAO.find(doctorId);
            if(doctor==null) {
                entity = new ResponseEntity<Doctor>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
            else {
                doctor.getSpecializations();
                entity = new ResponseEntity<Doctor>(doctor, new HttpHeaders(), HttpStatus.FOUND);

            }
        }
        catch (Exception e) {
            entity = new ResponseEntity<Doctor>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return entity;
    }

    @RequestMapping("/list")
    @Transactional
    public ResponseEntity<List<Doctor>> doctors() {

        ResponseEntity<List<Doctor>> entity = null;
        try {
            List<Doctor> doctors = doctorDAO.list();
            entity = new ResponseEntity<List<Doctor>>(doctors, new HttpHeaders(), HttpStatus.FOUND);
        }
        catch (Exception e) {
            entity = new ResponseEntity<List<Doctor>>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
            LOG.error(e);
        }

        return entity;
    }
}