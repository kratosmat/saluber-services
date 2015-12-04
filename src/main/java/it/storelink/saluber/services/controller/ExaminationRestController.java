package it.storelink.saluber.services.controller;

import it.storelink.saluber.services.model.Examination;
import it.storelink.saluber.services.service.ExaminationService;
import it.storelink.saluber.services.vo.ExaminationVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Service
@RequestMapping("/examination")
public class ExaminationRestController {


    private Log LOG = LogFactory.getLog(ExaminationRestController.class);

    @Autowired
    private ExaminationService examinationService;

    public void setExaminationService(ExaminationService examinationService){
        this.examinationService = examinationService;
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Examination> findById(@PathVariable Long id) {
        ResponseEntity<Examination> entity;
        try {
            Examination examination = examinationService.findById(id);
            if(examination==null) {
                entity = new ResponseEntity<Examination>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
            else {
                entity = new ResponseEntity<Examination>(examination, new HttpHeaders(), HttpStatus.OK);
            }
        }
        catch (Exception e) {
            entity = new ResponseEntity<Examination>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return entity;
    }

    @RequestMapping("/list")
    public ResponseEntity<List<ExaminationVO>> list() {

        ResponseEntity<List<ExaminationVO>> entity;
        try {
            List<ExaminationVO> examinations = examinationService.list();
            entity = new ResponseEntity<List<ExaminationVO>>(examinations, new HttpHeaders(), HttpStatus.OK);
        }
        catch (Exception e) {
            entity = new ResponseEntity<List<ExaminationVO>>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
            LOG.error(e);
        }

        return entity;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Long> save(@RequestBody ExaminationVO examination) {

        ResponseEntity<Long> entity;
        try {
            if(examination==null) {
                entity = new ResponseEntity<Long>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
            }
            else {
                Long id = examinationService.save(examination);
                entity = new ResponseEntity<Long>(id, new HttpHeaders(), HttpStatus.OK);
            }
        }
        catch (Exception e) {
            entity = new ResponseEntity<Long>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
            LOG.error(e);
        }
        return entity;
    }

}