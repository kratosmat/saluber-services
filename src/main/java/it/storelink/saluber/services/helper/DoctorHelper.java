package it.storelink.saluber.services.helper;


import it.storelink.saluber.services.model.Doctor;
import it.storelink.saluber.services.model.Hospital;
import it.storelink.saluber.services.model.Specialization;
import it.storelink.saluber.services.vo.DoctorVO;

/**
 * User: kratos
 * Date: 04/12/15
 * Time: 19.04
 */

public class DoctorHelper {

    public static Doctor vo2Entity(DoctorVO vo) {
        return null;
    }

    public static DoctorVO entity2VO(Doctor entity) {
        DoctorVO vo = new DoctorVO();

        vo.setId(entity.getId());
        vo.setFirstName(entity.getFirstName());
        vo.setLastName(entity.getLastName());
        vo.setBod(entity.getBod());
        vo.setCv(entity.getCv());
        vo.setDoctorId(entity.getDoctorId());
        vo.setPhone(entity.getPhone());
        for (Hospital hospital : entity.getHospitals()) {
            vo.getHospitals().add(hospital.getId());
        }
        for (Specialization specialization : entity.getSpecializations()) {
            vo.getSpecializations().add(specialization.getId());
        }

        return vo;
    }

}
