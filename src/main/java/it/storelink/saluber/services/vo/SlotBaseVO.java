package it.storelink.saluber.services.vo;

/**
 * User: kratos
 * Date: 06/12/15
 * Time: 16.03
 */

public class SlotBaseVO {

    protected Long _id;
    protected Long _day;
    protected String _start;
    protected String _end;

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

}
