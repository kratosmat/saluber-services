package it.storelink.saluber.services.service;

import it.storelink.saluber.services.dao.PatientDAO;
import it.storelink.saluber.services.model.Patient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PatientService {


    private Log LOG = LogFactory.getLog(PatientService.class);

    @Autowired
    private PatientDAO patientDAO;

    public void setPatientDAO(PatientDAO patientDAO){
        this.patientDAO = patientDAO;
    }

    @Transactional
    public List<Patient> list() throws BusinessException {
        try {
            List<Patient> patients = patientDAO.list();
            return patients;
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }

    @Transactional
    public Patient findById(Long id) throws BusinessException {
        try {
            //return DoctorHelper.entity2VO(doctorDAO.find(id));
            return patientDAO.find(id);
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