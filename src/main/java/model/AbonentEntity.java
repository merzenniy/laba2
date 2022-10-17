package model;

import javax.persistence.*;

@Entity
@Table(name = "abonent", schema = "var4", catalog = "")
public class AbonentEntity {
    private int idabonent;
    private String surnameab;
    private String nameab;
    private String fathnameab;
    private String addressab;
    private String loginA;
    private String passwordA;
    private CounterEntity counterByIdcounter;
    private TarifEntity tarifByIdtarif;

    @Override
    public String toString() {
        return surnameab+ " "+nameab;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idabonent", nullable = false)
    public int getIdabonent() {
        return idabonent;
    }

    public void setIdabonent(int idabonent) {
        this.idabonent = idabonent;
    }

    @Basic
    @Column(name = "surnameab", nullable = true, length = 40)
    public String getSurnameab() {
        return surnameab;
    }

    public void setSurnameab(String surnameab) {
        this.surnameab = surnameab;
    }

    @Basic
    @Column(name = "nameab", nullable = true, length = 40)
    public String getNameab() {
        return nameab;
    }

    public void setNameab(String nameab) {
        this.nameab = nameab;
    }

    @Basic
    @Column(name = "fathnameab", nullable = true, length = 40)
    public String getFathnameab() {
        return fathnameab;
    }

    public void setFathnameab(String fathnameab) {
        this.fathnameab = fathnameab;
    }

    @Basic
    @Column(name = "addressab", nullable = true, length = 50)
    public String getAddressab() {
        return addressab;
    }

    public void setAddressab(String addressab) {
        this.addressab = addressab;
    }

    @Basic
    @Column(name = "loginA", nullable = true, length = 30)
    public String getLoginA() {
        return loginA;
    }

    public void setLoginA(String loginA) {
        this.loginA = loginA;
    }

    @Basic
    @Column(name = "passwordA", nullable = true, length = 30)
    public String getPasswordA() {
        return passwordA;
    }

    public void setPasswordA(String passwordA) {
        this.passwordA = passwordA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbonentEntity that = (AbonentEntity) o;

        if (idabonent != that.idabonent) return false;
        if (surnameab != null ? !surnameab.equals(that.surnameab) : that.surnameab != null) return false;
        if (nameab != null ? !nameab.equals(that.nameab) : that.nameab != null) return false;
        if (fathnameab != null ? !fathnameab.equals(that.fathnameab) : that.fathnameab != null) return false;
        if (addressab != null ? !addressab.equals(that.addressab) : that.addressab != null) return false;
        if (loginA != null ? !loginA.equals(that.loginA) : that.loginA != null) return false;
        if (passwordA != null ? !passwordA.equals(that.passwordA) : that.passwordA != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idabonent;
        result = 31 * result + (surnameab != null ? surnameab.hashCode() : 0);
        result = 31 * result + (nameab != null ? nameab.hashCode() : 0);
        result = 31 * result + (fathnameab != null ? fathnameab.hashCode() : 0);
        result = 31 * result + (addressab != null ? addressab.hashCode() : 0);
        result = 31 * result + (loginA != null ? loginA.hashCode() : 0);
        result = 31 * result + (passwordA != null ? passwordA.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idcounter", referencedColumnName = "idcounter", nullable = false)
    public CounterEntity getCounterByIdcounter() {
        return counterByIdcounter;
    }

    public void setCounterByIdcounter(CounterEntity counterByIdcounter) {
        this.counterByIdcounter = counterByIdcounter;
    }

    @ManyToOne
    @JoinColumn(name = "idtarif", referencedColumnName = "idtarif", nullable = false)
    public TarifEntity getTarifByIdtarif() {
        return tarifByIdtarif;
    }

    public void setTarifByIdtarif(TarifEntity tarifByIdtarif) {
        this.tarifByIdtarif = tarifByIdtarif;
    }
}
