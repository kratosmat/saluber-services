package it.storelink.saluber.services.service;

import it.storelink.saluber.services.dao.*;
import it.storelink.saluber.services.model.*;
import it.storelink.saluber.services.vo.BookingVO;
import it.storelink.saluber.services.vo.ExaminationVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedList;
import java.util.List;

@Service
public class ExaminationService {


    private Log LOG = LogFactory.getLog(ExaminationService.class);

    @Autowired
    private ExaminationDAO examinationDAO;

    public void setExaminationDAO(ExaminationDAO examinationDAO){
        this.examinationDAO = examinationDAO;
    }

    @Autowired
    private BookingDAO bookingDAO;

    public void setBookingDAO(BookingDAO bookingDAO){
        this.bookingDAO = bookingDAO;
    }

    @Autowired
    private MedicalTestDAO medicalTestDAO;

    public void setMedicalTestDAO(MedicalTestDAO medicalTestDAO){
        this.medicalTestDAO = medicalTestDAO;
    }

    @Transactional
    public List<ExaminationVO> list() throws BusinessException {
        try {
            List<Examination> examinations = examinationDAO.list();
            List<ExaminationVO> examinationVOs = new LinkedList<ExaminationVO>();

            for (Examination examination : examinations) {
                ExaminationVO examinationVO = new ExaminationVO();
                examinationVO.setId(examination.getId());
                examinationVO.setDate(examination.getDate());
                examinationVO.setResults(examination.getResults());
                examinationVO.setFeedback(examination.getFeedback());
                examinationVO.setPayment(examination.getPayment());
                examinationVO.setBookingId(examination.getBooking().getId());
                for(MedicalTestType medicalTestType : examination.getMedicalTestTypes()) {
                    examinationVO.getMedicalTestTypeIDs().add(medicalTestType.getId());
                }
                examinationVOs.add(examinationVO);
            }
            return examinationVOs;
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }

    @Transactional
    public Examination findById(Long id) throws BusinessException {
        try {
            return examinationDAO.find(id);
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
    }

    @Transactional
    public Long save(ExaminationVO examinationVO) throws BusinessException {
        Examination entExamination;
        try {
            entExamination = new Examination();
            entExamination.setDate(examinationVO.getDate());
            entExamination.setPayment(examinationVO.getPayment());
            entExamination.setFeedback(examinationVO.getFeedback());
            entExamination.setResults(examinationVO.getResults());

            Booking booking = bookingDAO.find(examinationVO.getBookingId());
            entExamination.setBooking(booking);

            for(Long medicalTestId : examinationVO.getMedicalTestTypeIDs()) {
                MedicalTestType medicalTestType = medicalTestDAO.find(medicalTestId);
                entExamination.getMedicalTestTypes().add(medicalTestType);
            }

            examinationDAO.save(entExamination);
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusinessException(e);
        }
        return entExamination.getId();
    }

}