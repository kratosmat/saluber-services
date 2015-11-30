package it.storelink.saluber.services.vo;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * User: kratos
 * Date: 28/11/15
 * Time: 15.45
 */

public class ExaminationVO {

    private Long _id;
    private Date _date;
    private Long _bookingId;
    private Double _payment;
    private Integer _feedback;
    private List<Long> _medicalTestTypeIDs = new LinkedList<Long>();
    private String _results;

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        this._id = id;
    }

    public Date getDate() {
        return _date;
    }

    public void setDate(Date date) {
        this._date = date;
    }

    public Double getPayment() {
        return _payment;
    }

    public void setPayment(Double payment) {
        this._payment = payment;
    }

    public Integer getFeedback() {
        return _feedback;
    }

    public void setFeedback(Integer feedback) {
        this._feedback = feedback;
    }

    public String getResults() {
        return _results;
    }

    public void setResults(String results) {
        this._results = results;
    }

    public Long getBookingId() {
        return _bookingId;
    }

    public void setBookingId(Long bookingId) {
        this._bookingId = bookingId;
    }

    public List<Long> getMedicalTestTypeIDs() {
        return _medicalTestTypeIDs;
    }

    public void setMedicalTestTypeIDs(List<Long> medicalTestTypeIDs) {
        this._medicalTestTypeIDs = medicalTestTypeIDs;
    }

}
