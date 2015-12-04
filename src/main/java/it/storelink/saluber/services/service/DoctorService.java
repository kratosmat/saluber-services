package it.storelink.saluber.services.service;

import it.storelink.saluber.services.dao.*;
import it.storelink.saluber.services.helper.DoctorHelper;
import it.storelink.saluber.services.model.*;
import it.storelink.saluber.services.vo.DoctorVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class DoctorService {


    private Log LOG = LogFactory.getLog(DoctorService.class);

    @Autowired
    private DoctorDAO doctorDAO;

    public void setDoctorDAO(DoctorDAO doctorDAO){
        this.doctorDAO = doctorDAO;
    }

    @Transactional
    public List<DoctorVO> list() throws BusinessException {
        List<DoctorVO> doctorVOs = new LinkedList<DoctorVO>();
        try {
            List<Doctor> doctors = doctorDAO.list();
            for (Doctor doctor : doctors) {
                DoctorVO doctorVO = DoctorHelper.entity2VO(doctor);
                doctorVOs.add(doctorVO);
            }
            return doctorVOs;
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }

    @Transactional
    public DoctorVO findById(Long id) throws BusinessException {
        try {
            return DoctorHelper.entity2VO(doctorDAO.find(id));
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }

    @Transactional
    public Long save(DoctorVO doctorVO) throws BusinessException {
        //TODO: implementare la logica del salvataggio
        return -1l;
    }

}