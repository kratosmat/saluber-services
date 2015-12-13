package it.storelink.saluber.services.vo;


import java.util.LinkedList;
import java.util.List;

/**
 * User: kratos
 * Date: 06/12/15
 * Time: 16.03
 */

public class DayExtendedVO {
    private Long _id;
    private Integer _number;
    private List<SlotExtendedVO> _slots = new LinkedList<SlotExtendedVO>();
    private Long _month;


    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        this._id = id;
    }

    public Integer getNumber() {
        return _number;
    }

    public void setNumber(Integer number) {
        this._number = number;
    }

    public List<SlotExtendedVO> getSlots() {
        return _slots;
    }

    public void setSlots(List<SlotExtendedVO> slots) {
        this._slots = slots;
    }

    public Long getMonth() {
        return _month;
    }

    public void setMonth(Long month) {
        this._month = month;
    }

}
