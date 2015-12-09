package it.storelink.saluber.services.service;

import it.storelink.saluber.services.dao.StationDAO;
import it.storelink.saluber.services.helper.StationHelper;
import it.storelink.saluber.services.model.Station;
import it.storelink.saluber.services.vo.StationVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
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
    public List<StationVO> list() throws BusinessException {
        List<StationVO> stationVOs = new LinkedList<StationVO>();
        try {

            List<Station> stations = stationDAO.list();
            for(Station station : stations) {
                stationVOs.add(StationHelper.entity2VO(station));
            }
            return stationVOs;
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }

    @Transactional
    public StationVO findById(Long id) throws BusinessException {
        try {
            return StationHelper.entity2VO(stationDAO.find(id));
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }

    @Transactional
    public Long save(Station station) throws BusinessException {
        //TODO: implementare la logica del salvataggio
        return -1l;
    }

}