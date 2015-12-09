package it.storelink.saluber.services.model;


import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * User: kratos
 * Date: 06/12/15
 * Time: 16.03
 */

@Entity
@Table(name = "DAYS", catalog = "")
public class Day {
    private Long _id;
    private Integer _number;
    private List<Slot> _slots = new LinkedList<Slot>();
    private Month _month;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqGen")
    @SequenceGenerator(name = "SeqGen", sequenceName = "SEQ_DAY", allocationSize = 1)
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, precision = -127)
    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        this._id = id;
    }

    @Basic
    @Column(name = "NUMBER", nullable = true, insertable = true, updatable = true)
    public Integer getNumber() {
        return _number;
    }

    public void setNumber(Integer number) {
        this._number = number;
    }

    @OneToMany(mappedBy="day", fetch = FetchType.LAZY)
    @OrderBy("id ASC")
    public List<Slot> getSlots() {
        return _slots;
    }

    public void setSlots(List<Slot> slots) {
        this._slots = slots;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MONTH")
    public Month getMonth() {
        return _month;
    }

    public void setMonth(Month month) {
        this._month = month;
    }

}
