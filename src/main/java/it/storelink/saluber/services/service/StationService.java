package it.storelink.saluber.services.service;

import it.storelink.saluber.services.dao.StationDAO;
import it.storelink.saluber.services.model.Patient;
import it.storelink.saluber.services.model.Station;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StationService {


    private Log LOG = LogFactory.getLog(StationService.class);

    @Autowired
    private StationDAO stationDAO;

    public void setStationDAO(StationDAO stationDAO){
        this.stationDAO = stationDAO;
    }

    @Transactional
    public List<Station> list() throws BusinessException {
        try {
            List<Station> stations = stationDAO.list();
            return stations;
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }

    @Transactional
    public Station findById(Long id) throws BusinessException {
        try {
            return stationDAO.find(id);
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }

    @Transactional
    public Long save(Patient patient) throws BusinessException {
        //TODO: implementare la logica del salvataggio
        return -1l;
    }

}