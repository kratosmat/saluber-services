package it.storelink.saluber.services.controller;

import it.storelink.saluber.services.dao.PatientDAO;
import it.storelink.saluber.services.model.Patient;
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
@RequestMapping("/patient")
public class PatientRestController {

    @Autowired
    private PatientDAO patientDAO;

    private Log LOG = LogFactory.getLog(PatientRestController.class);

    public void setPatientDAO(PatientDAO patientDAO){
        this.patientDAO = patientDAO;
    }

    @RequestMapping("/{patientId}")
    @Transactional
    public ResponseEntity<Patient> doctor(@PathVariable Long patientId) {
        ResponseEntity<Patient> entity = null;
        try {
            Patient patient = patientDAO.find(patientId);
            if(patientId==null) {
                entity = new ResponseEntity<Patient>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
            else {
                entity = new ResponseEntity<Patient>(patient, new HttpHeaders(), HttpStatus.FOUND);
            }
        }
        catch (Exception e) {
            entity = new ResponseEntity<Patient>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return entity;
    }

    @RequestMapping("/list")
    @Transactional
    public ResponseEntity<List<Patient>> patients() {

        ResponseEntity<List<Patient>> entity = null;
        try {
            List<Patient> patients = patientDAO.list();
            entity = new ResponseEntity<List<Patient>>(patients, new HttpHeaders(), HttpStatus.FOUND);
        }
        catch (Exception e) {
            entity = new ResponseEntity<List<Patient>>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
            LOG.error(e);
        }

        return entity;
    }
}