package it.storelink.saluber.services.controller;

import it.storelink.saluber.services.model.Station;
import it.storelink.saluber.services.service.StationService;
import it.storelink.saluber.services.vo.StationVO;
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
@RequestMapping("/api/station")
public class StationRestController {

    private Log LOG = LogFactory.getLog(StationRestController.class);

    @Autowired
    private StationService stationService;

    public void setStationService(StationService stationService){
        this.stationService = stationService;
    }

    @RequestMapping("/{stationId}")
    @Transactional
    public ResponseEntity<StationVO> station(@PathVariable Long stationId) {
        ResponseEntity<StationVO> entity = null;
        try {
            StationVO station = stationService.findById(stationId);
            if(station==null) {
                entity = new ResponseEntity<StationVO>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
            else {
                entity = new ResponseEntity<StationVO>(station, new HttpHeaders(), HttpStatus.OK);
            }
        }
        catch (Exception e) {
            LOG.error(e);
            entity = new ResponseEntity<StationVO>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return entity;
    }

    @RequestMapping("/list")
    @Transactional
    public ResponseEntity<List<StationVO>> stations() {

        ResponseEntity<List<StationVO>> entity = null;
        try {
            List<StationVO> stations = stationService.list();
            entity = new ResponseEntity<List<StationVO>>(stations, new HttpHeaders(), HttpStatus.OK);
        }
        catch (Exception e) {
            entity = new ResponseEntity<List<StationVO>>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
            LOG.error(e);
        }

        return entity;
    }

}