package it.storelink.saluber.services.helper;

import it.storelink.saluber.services.model.*;
import it.storelink.saluber.services.vo.*;

/**
 * User: kratos
 * Date: 07/12/15
 * Time: 14.27
 */

public class MonthHelper {

    public static MonthVO entity2VO(Month month) {


        MonthVO monthVO = new MonthVO();
        monthVO.setOrganizationId(month.getOrganizationId());

        /*
        if(month instanceof MonthDoctor) {
            monthVO = new MonthDoctorVO();
            ((MonthDoctorVO) monthVO).setUsername(((MonthDoctor) month).getUsername());
        }
        else  if(month instanceof MonthStation) {
            monthVO = new MonthStationVO();
            ((MonthStationVO)monthVO).setStationId(((MonthStation) month).getStationId());
        }
        */

        monthVO.setId(month.getId());
        monthVO.setMonth(month.getMonth());
        monthVO.setYear(month.getYear());
        monthVO.setType(month.getType());

        for(Day day : month.getDays()) {
            DayVO dayVO = new DayVO();
            dayVO.setId(day.getId());
            dayVO.setNumber(day.getNumber());
            dayVO.setMonth(monthVO.getId());

            for (Slot slot : day.getSlots()) {
                SlotVO slotVO = new SlotVO();
                slotVO.setId(slot.getId());
                slotVO.setStart(slot.getStart());
                slotVO.setEnd(slot.getEnd());
                slotVO.setSelected(slot.getSelected());
                slotVO.setDay(day.getId());
                dayVO.getSlots().add(slotVO);
            }

            monthVO.getDays().add(dayVO);
        }

        return monthVO;
    }

    /*
    public static Month vo2Entity(MonthVO monthVO) {
        Month month = null;
        if(monthVO instanceof MonthDoctorVO) {
            month = new MonthDoctor();
            month.setId(monthVO.getId());
            month.setMonth(monthVO.getMonth());
            month.setYear(monthVO.getYear());
            ((MonthDoctor) month).setUsername(((MonthDoctorVO) monthVO).getUsername());

            for(DayVO dayVO : monthVO.getDays()) {
                Day day = new Day();
                day.setId(dayVO.getId());
                day.setNumber(dayVO.getNumber());
                day.setMonth(month);

                for (SlotVO slotVO : dayVO.getSlots()) {
                    Slot slot = new Slot();
                    slot.setId(slotVO.getId());
                    slot.setStart(slotVO.getStart());
                    slot.setEnd(slotVO.getEnd());
                    slot.setSelected(slotVO.getSelected());
                    slot.setDay(day);

                    day.getSlots().add(slot);
                }

                month.getDays().add(day);
            }
        }

        return month;
    }
    */
}
