package it.storelink.saluber.services.model;


import javax.persistence.*;
import java.util.List;

/**
 * User: kratos
 * Date: 06/12/15
 * Time: 16.05
 */
@Entity
@Inheritance
@DiscriminatorColumn(name="ROLE_TYPE")
@Table(name="MONTHS")
public abstract class Month {

    private Long _id;
    private String _month;
    private String _year;
    private List<Day> _days;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqGen")
    @SequenceGenerator(name = "SeqGen", sequenceName = "SEQ_MONTH", allocationSize = 1)
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, precision = -127)
    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        this._id = id;
    }

    @Basic
    @Column(name = "MONTH", nullable = true, insertable = true, updatable = true, length = 255)
    public String getMonth() {
        return _month;
    }

    public void setMonth(String month) {
        this._month = month;
    }

    @Basic
    @Column(name = "YEAR", nullable = true, insertable = true, updatable = true, length = 255)
    public String getYear() {
        return _year;
    }

    public void setYear(String year) {
        this._year = year;
    }

    @OneToMany(mappedBy="month")
    public List<Day> getDays() {
        return _days;
    }

    public void setDays(List<Day> days) {
        this._days = days;
    }
}
