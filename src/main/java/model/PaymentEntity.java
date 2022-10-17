package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "payment", schema = "var4", catalog = "")
public class PaymentEntity {
    private int idpayment;
    private Date datepayment;
    private BigDecimal sum;
    private AbonentEntity abonentByIdabonent;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idpayment", nullable = false)
    public int getIdpayment() {
        return idpayment;
    }

    public void setIdpayment(int idpayment) {
        this.idpayment = idpayment;
    }

    @Basic
    @Column(name = "datepayment", nullable = true)
    public Date getDatepayment() {
        return datepayment;
    }

    public void setDatepayment(Date datepayment) {
        this.datepayment = datepayment;
    }

    @Basic
    @Column(name = "sum", nullable = true, precision = 2)
    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentEntity that = (PaymentEntity) o;

        if (idpayment != that.idpayment) return false;
        if (datepayment != null ? !datepayment.equals(that.datepayment) : that.datepayment != null) return false;
        if (sum != null ? !sum.equals(that.sum) : that.sum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idpayment;
        result = 31 * result + (datepayment != null ? datepayment.hashCode() : 0);
        result = 31 * result + (sum != null ? sum.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idabonent", referencedColumnName = "idabonent", nullable = false)
    public AbonentEntity getAbonentByIdabonent() {
        return abonentByIdabonent;
    }

    public void setAbonentByIdabonent(AbonentEntity abonentByIdabonent) {
        this.abonentByIdabonent = abonentByIdabonent;
    }
}
