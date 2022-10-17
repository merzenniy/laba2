package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "counterValue", schema = "var4", catalog = "")
public class CounterValueEntity {
    private int idvalue;
    private BigDecimal valueamount;
    private Date datetaking;
    private CounterEntity counterByIdcounter;
    private AbonentEntity abonentByIdAbonent;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idvalue", nullable = false)
    public int getIdvalue() {
        return idvalue;
    }

    public void setIdvalue(int idvalue) {
        this.idvalue = idvalue;
    }

    @Basic
    @Column(name = "valueamount", nullable = true, precision = 2)
    public BigDecimal getValueamount() {
        return valueamount;
    }

    public void setValueamount(BigDecimal valueamount) {
        this.valueamount = valueamount;
    }

    @Basic
    @Column(name = "datetaking", nullable = true)
    public Date getDatetaking() {
        return datetaking;
    }

    public void setDatetaking(Date datetaking) {
        this.datetaking = datetaking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CounterValueEntity that = (CounterValueEntity) o;

        if (idvalue != that.idvalue) return false;
        if (valueamount != null ? !valueamount.equals(that.valueamount) : that.valueamount != null) return false;
        if (datetaking != null ? !datetaking.equals(that.datetaking) : that.datetaking != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idvalue;
        result = 31 * result + (valueamount != null ? valueamount.hashCode() : 0);
        result = 31 * result + (datetaking != null ? datetaking.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idcounter", referencedColumnName = "idcounter")
    public CounterEntity getCounterByIdcounter() {
        return counterByIdcounter;
    }

    public void setCounterByIdcounter(CounterEntity counterByIdcounter) {
        this.counterByIdcounter = counterByIdcounter;
    }

    @ManyToOne
    @JoinColumn(name = "idAbonent", referencedColumnName = "idabonent")
    public AbonentEntity getAbonentByIdAbonent() {
        return abonentByIdAbonent;
    }

    public void setAbonentByIdAbonent(AbonentEntity abonentByIdAbonent) {
        this.abonentByIdAbonent = abonentByIdAbonent;
    }
}
