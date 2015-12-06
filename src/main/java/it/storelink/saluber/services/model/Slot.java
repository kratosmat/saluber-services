package it.storelink.saluber.services.model;


import javax.persistence.*;

/**
 * User: kratos
 * Date: 06/12/15
 * Time: 16.03
 */

@Entity
@Table(name = "SLOTS", catalog = "")
public class Slot {
    private Long _id;
    private Day _day;
    private String _start;
    private String _end;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqGen")
    @SequenceGenerator(name = "SeqGen", sequenceName = "SEQ_SLOT", allocationSize = 1)
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, precision = -127)
    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        this._id = id;
    }

    @Basic
    @Column(name = "START_DATE", nullable = true, insertable = true, updatable = true, length = 255)
    public String getStart() {
        return _start;
    }

    public void setStart(String start) {
        this._start = start;
    }

    @Basic
    @Column(name = "END_DATE", nullable = true, insertable = true, updatable = true, length = 255)
    public String getEnd() {
        return _end;
    }

    public void setEnd(String end) {
        this._end = end;
    }

    @ManyToOne
    @JoinColumn(name="DAY")
    public Day getDay() {
        return _day;
    }

    public void setDay(Day day) {
        this._day = day;
    }

}
