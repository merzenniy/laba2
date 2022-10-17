package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "tarif", schema = "var4", catalog = "")
public class TarifEntity {
    private int idtarif;
    private String nametarif;
    private BigDecimal pricekilo;
    private Date dateconnect;
    private String opys;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idtarif", nullable = false)
    public int getIdtarif() {
        return idtarif;
    }

    public void setIdtarif(int idtarif) {
        this.idtarif = idtarif;
    }

    @Basic
    @Column(name = "nametarif", nullable = true, length = 30)
    public String getNametarif() {
        return nametarif;
    }

    public void setNametarif(String nametarif) {
        this.nametarif = nametarif;
    }

    @Basic
    @Column(name = "pricekilo", nullable = false, precision = 2)
    public BigDecimal getPricekilo() {
        return pricekilo;
    }

    public void setPricekilo(BigDecimal pricekilo) {
        this.pricekilo = pricekilo;
    }

    @Basic
    @Column(name = "dateconnect", nullable = true)
    public Date getDateconnect() {
        return dateconnect;
    }

    public void setDateconnect(Date dateconnect) {
        this.dateconnect = dateconnect;
    }

    @Basic
    @Column(name = "opys", nullable = true, length = -1)
    public String getOpys() {
        return opys;
    }

    public void setOpys(String opys) {
        this.opys = opys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TarifEntity that = (TarifEntity) o;

        if (idtarif != that.idtarif) return false;
        if (nametarif != null ? !nametarif.equals(that.nametarif) : that.nametarif != null) return false;
        if (pricekilo != null ? !pricekilo.equals(that.pricekilo) : that.pricekilo != null) return false;
        if (dateconnect != null ? !dateconnect.equals(that.dateconnect) : that.dateconnect != null) return false;
        if (opys != null ? !opys.equals(that.opys) : that.opys != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idtarif;
        result = 31 * result + (nametarif != null ? nametarif.hashCode() : 0);
        result = 31 * result + (pricekilo != null ? pricekilo.hashCode() : 0);
        result = 31 * result + (dateconnect != null ? dateconnect.hashCode() : 0);
        result = 31 * result + (opys != null ? opys.hashCode() : 0);
        return result;
    }
}
