package it.storelink.saluber.services.vo;


import it.storelink.saluber.services.model.Slot;

/**
 * User: kratos
 * Date: 06/12/15
 * Time: 16.03
 */

public class SlotVO {
    private Long _id;
    private Long _day;
    private String _start;
    private String _end;
    private Boolean _selected;

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        this._id = id;
    }

    public String getStart() {
        return _start;
    }

    public void setStart(String start) {
        this._start = start;
    }

    public String getEnd() {
        return _end;
    }

    public void setEnd(String end) {
        this._end = end;
    }

    public Long getDay() {
        return _day;
    }

    public void setDay(Long day) {
        this._day = day;
    }

    public Boolean getSelected() {
        return _selected;
    }

    public void setSelected(Boolean selected) {
        this._selected = selected;
    }

}
