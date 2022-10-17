package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "accident", schema = "var4", catalog = "")
public class AccidentEntity {
    private int idaccident;
    private String addressac;
    private BigDecimal loss;
    private String desription;
    private Date dateac;
    private AbonentEntity abonentByIdabonent;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idaccident", nullable = false)
    public int getIdaccident() {
        return idaccident;
    }

    public void setIdaccident(int idaccident) {
        this.idaccident = idaccident;
    }

    @Basic
    @Column(name = "addressac", nullable = true, length = 50)
    public String getAddressac() {
        return addressac;
    }

    public void setAddressac(String addressac) {
        this.addressac = addressac;
    }

    @Basic
    @Column(name = "loss", nullable = true, precision = 2)
    public BigDecimal getLoss() {
        return loss;
    }

    public void setLoss(BigDecimal loss) {
        this.loss = loss;
    }

    @Basic
    @Column(name = "desription", nullable = true, length = -1)
    public String getDesription() {
        return desription;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }

    @Basic
    @Column(name = "dateac", nullable = true)
    public Date getDateac() {
        return dateac;
    }

    public void setDateac(Date dateac) {
        this.dateac = dateac;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccidentEntity accident = (AccidentEntity) o;

        if (idaccident != accident.idaccident) return false;
        if (addressac != null ? !addressac.equals(accident.addressac) : accident.addressac != null) return false;
        if (loss != null ? !loss.equals(accident.loss) : accident.loss != null) return false;
        if (desription != null ? !desription.equals(accident.desription) : accident.desription != null) return false;
        if (dateac != null ? !dateac.equals(accident.dateac) : accident.dateac != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idaccident;
        result = 31 * result + (addressac != null ? addressac.hashCode() : 0);
        result = 31 * result + (loss != null ? loss.hashCode() : 0);
        result = 31 * result + (desription != null ? desription.hashCode() : 0);
        result = 31 * result + (dateac != null ? dateac.hashCode() : 0);
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
