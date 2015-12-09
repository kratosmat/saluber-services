package it.storelink.saluber.services.model;


import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * User: kratos
 * Date: 06/12/15
 * Time: 16.05
 */
@Entity
//@Inheritance
//@DiscriminatorColumn(name="ROLE_TYPE")
@Table(name="MONTHS")
public class Month {

    private Long _id;
    private Integer _month;
    private Integer _year;
    private Long organizationId;
    private String type;
    private List<Day> _days = new LinkedList<Day>();

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
    @Column(name = "MONTH", nullable = true, insertable = true, updatable = true)
    public Integer getMonth() {
        return _month;
    }

    public void setMonth(Integer month) {
        this._month = month;
    }

    @Basic
    @Column(name = "YEAR", nullable = true, insertable = true, updatable = true)
    public Integer getYear() {
        return _year;
    }

    public void setYear(Integer year) {
        this._year = year;
    }

    @OneToMany(mappedBy="month", fetch = FetchType.LAZY)
    @OrderBy("id ASC")
    public List<Day> getDays() {
        return _days;
    }

    public void setDays(List<Day> days) {
        this._days = days;
    }



    @Basic
    @Column(name = "ORGANIZATION_ID", nullable = true, insertable = true, updatable = true)
    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    @Basic
    @Column(name = "TYPE", nullable = false, insertable = true, updatable = true, length = 255)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
