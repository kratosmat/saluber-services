package it.storelink.saluber.services.controller;

import it.storelink.saluber.services.model.Hospital;
import it.storelink.saluber.services.model.MedicalTestType;
import it.storelink.saluber.services.model.Specialization;
import it.storelink.saluber.services.service.ReferenceEntityService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/reference")
public class ReferenceEntityRestController {

    private Log LOG = LogFactory.getLog(ReferenceEntityRestController.class);

    @Autowired
    private ReferenceEntityService referenceEntityService;

    public void setReferenceEntityService(ReferenceEntityService referenceEntityService){
        this.referenceEntityService = referenceEntityService;
    }

    @RequestMapping("/hospitals")
    @Transactional
    public ResponseEntity<List<Hospital>> hospitals() {
        ResponseEntity<List<Hospital>> entity = null;
        try {

            List<Hospital> list = referenceEntityService.hospitals();

            if(list==null) {
                entity = new ResponseEntity<List<Hospital>>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
            else {
                entity = new ResponseEntity<List<Hospital>>(list, new HttpHeaders(), HttpStatus.OK);
            }
        }
        catch (Exception e) {
            LOG.error(e);
            entity = new ResponseEntity<List<Hospital>>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return entity;
    }

    @RequestMapping("/medicaltests")
    @Transactional
    public ResponseEntity<List<MedicalTestType>> medicalTests() {
        ResponseEntity<List<MedicalTestType>> entity = null;
        try {

            List<MedicalTestType> list = referenceEntityService.medicalTestTypes();

            if(list==null) {
                entity = new ResponseEntity<List<MedicalTestType>>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
            else {
                entity = new ResponseEntity<List<MedicalTestType>>(list, new HttpHeaders(), HttpStatus.OK);
            }
        }
        catch (Exception e) {
            LOG.error(e);
            entity = new ResponseEntity<List<MedicalTestType>>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return entity;
    }


    @RequestMapping("/specializations")
    @Transactional
    public ResponseEntity<List<Specialization>> specializations() {
        ResponseEntity<List<Specialization>> entity = null;
        try {

            List<Specialization> list = referenceEntityService.specializations();

            if(list==null) {
                entity = new ResponseEntity<List<Specialization>>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
            else {
                entity = new ResponseEntity<List<Specialization>>(list, new HttpHeaders(), HttpStatus.OK);
            }
        }
        catch (Exception e) {
            LOG.error(e);
            entity = new ResponseEntity<List<Specialization>>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return entity;
    }

}