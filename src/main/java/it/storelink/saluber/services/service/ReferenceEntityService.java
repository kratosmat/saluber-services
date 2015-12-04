package it.storelink.saluber.services.service;

import it.storelink.saluber.services.dao.*;
import it.storelink.saluber.services.helper.BookingHelper;
import it.storelink.saluber.services.model.*;
import it.storelink.saluber.services.vo.BookingVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class ReferenceEntityService {


    private Log LOG = LogFactory.getLog(ReferenceEntityService.class);

    @Autowired
    private HospitalDAO hospitalDAO;

    public void setHospitalDAO(HospitalDAO hospitalDAO){
        this.hospitalDAO = hospitalDAO;
    }

    @Autowired
    private MedicalTestDAO medicalTestDAO;

    public void setMedicalTestDAO(MedicalTestDAO medicalTestDAO){
        this.medicalTestDAO = medicalTestDAO;
    }

    @Autowired
    private SpecializationDAO specializationDAO;

    public void setSpecializationDAO(SpecializationDAO specializationDAO){
        this.specializationDAO = specializationDAO;
    }

    @Transactional
    public List<Specialization> specializations() throws BusinessException {
        try {
            return specializationDAO.list();
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }

    @Transactional
    public List<Hospital> hospitals() throws BusinessException {
        try {
            return hospitalDAO.list();
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }

    @Transactional
    public List<MedicalTestType> medicalTestTypes() throws BusinessException {
        try {
            return medicalTestDAO.list();
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }

}