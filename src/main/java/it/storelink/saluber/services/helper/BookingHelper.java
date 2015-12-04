package it.storelink.saluber.services.helper;

import it.storelink.saluber.services.model.*;
import it.storelink.saluber.services.vo.BookingVO;

/**
 * User: kratos
 * Date: 04/12/15
 * Time: 15.39
 */

public class BookingHelper {

    public static BookingVO entity2VO(Booking entity) {
        BookingVO vo = new BookingVO();
        vo.setId(entity.getId());
        vo.setDateStart(entity.getDateStart());
        vo.setDoctorId(entity.getDoctor().getId());
        vo.setPatientId(entity.getPatient().getId());
        vo.setStationId(entity.getStation().getId());
        vo.setSpecializationId(entity.getSpecialization().getId());
        return vo;
    }

    public static Booking vo2Entity(BookingVO bookingVO) {
        Booking entity = new Booking();
        entity.setDateStart(bookingVO.getDateStart());
        return entity;
    }

}
