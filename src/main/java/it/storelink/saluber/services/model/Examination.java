package it.storelink.saluber.services.model;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * User: kratos
 * Date: 28/11/15
 * Time: 15.45
 */

@Entity
@Table(name = "EXAMINATION", catalog = "")
public class Examination {

    private Long _id;
    private Date _date;
    private Booking _booking;
    private Double _payment;
    private Integer _feedback;
    private List<MedicalTestType> _medicalTestTypes = new LinkedList<MedicalTestType>();
    private String _results;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqGen")
    @SequenceGenerator(name = "SeqGen", sequenceName = "SEQ_EXAMINATION", allocationSize = 1)
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, precision = -127)
    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        this._id = id;
    }

    @Basic
    @Column(name = "DATE", nullable = false, insertable = true, updatable = true)
    public Date getDate() {
        return _date;
    }

    public void setDate(Date date) {
        this._date = date;
    }

    @OneToOne
    @JoinColumn(name = "BOOKING", referencedColumnName = "ID", nullable = false)
    public Booking getBooking() {
        return _booking;
    }

    public void setBooking(Booking booking) {
        this._booking = booking;
    }

    @Basic
    @Column(name = "PAYMENT", nullable = false, insertable = true, updatable = true)
    public Double getPayment() {
        return _payment;
    }

    public void setPayment(Double payment) {
        this._payment = payment;
    }

    @Basic
    @Column(name = "FEEDBACK", nullable = false, insertable = true, updatable = true)
    public Integer getFeedback() {
        return _feedback;
    }

    public void setFeedback(Integer feedback) {
        this._feedback = feedback;
    }

    @Basic
    @Column(name = "RESULTS", nullable = false, insertable = true, updatable = true)
    public String getResults() {
        return _results;
    }

    public void setResults(String results) {
        this._results = results;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="TESTTYPEXEXAMINATION")
    public List<MedicalTestType> getMedicalTestTypes() {
        return _medicalTestTypes;
    }

    public void setMedicalTestTypes(List<MedicalTestType> medicalTestTypes) {
        this._medicalTestTypes = medicalTestTypes;
    }

}
