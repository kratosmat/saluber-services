package it.storelink.saluber.services.controller;

import it.storelink.saluber.services.model.Doctor;
import it.storelink.saluber.services.service.DoctorService;
import it.storelink.saluber.services.vo.DoctorVO;
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

    private Log LOG = LogFactory.getLog(DoctorRestController.class);

    @Autowired
    private DoctorService doctorService;

    public void setDoctorService(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @RequestMapping("/{doctorId}")
    @Transactional
    public ResponseEntity<DoctorVO> doctor(@PathVariable Long doctorId) {
        ResponseEntity<DoctorVO> entity = null;
        try {
            DoctorVO doctor = doctorService.findById(doctorId);
            if(doctor==null) {
                entity = new ResponseEntity<DoctorVO>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
            else {
                doctor.getSpecializations();
                entity = new ResponseEntity<DoctorVO>(doctor, new HttpHeaders(), HttpStatus.FOUND);

            }
        }
        catch (Exception e) {
            entity = new ResponseEntity<DoctorVO>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return entity;
    }

    @RequestMapping("/list")
    @Transactional
    public ResponseEntity<List<DoctorVO>> doctors() {

        ResponseEntity<List<DoctorVO>> entity = null;
        try {
            List<DoctorVO> doctors = doctorService.list();
            entity = new ResponseEntity<List<DoctorVO>>(doctors, new HttpHeaders(), HttpStatus.FOUND);
        }
        catch (Exception e) {
            entity = new ResponseEntity<List<DoctorVO>>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
            LOG.error(e);
        }

        return entity;
    }

}