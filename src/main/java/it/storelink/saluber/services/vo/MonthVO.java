package it.storelink.saluber.services.vo;

import java.util.LinkedList;
import java.util.List;

/**
 * User: kratos
 * Date: 06/12/15
 * Time: 16.05
 */
public class MonthVO {

    private Long _id;
    private Integer _month;
    private Integer _year;
    private List<DayVO> _days = new LinkedList<DayVO>();
    private Long organizationId;
    private String type;

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        this._id = id;
    }

    public Integer getMonth() {
        return _month;
    }

    public void setMonth(Integer month) {
        this._month = month;
    }

    public Integer getYear() {
        return _year;
    }

    public void setYear(Integer year) {
        this._year = year;
    }

    public List<DayVO> getDays() {
        return _days;
    }

    public void setDays(List<DayVO> days) {
        this._days = days;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
