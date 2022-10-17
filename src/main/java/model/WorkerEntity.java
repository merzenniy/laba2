package model;

import javax.persistence.*;

@Entity
@Table(name = "worker", schema = "var4", catalog = "")
public class WorkerEntity {
    private int idworker;
    private String surnamew;
    private String namew;
    private String fathnamew;
    private String telephone;
    private String loginW;
    private String passwordW;

        public String toString(){return surnamew+ " "+namew;}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idworker", nullable = false)
    public int getIdworker() {
        return idworker;
    }

    public void setIdworker(int idworker) {
        this.idworker = idworker;
    }

    @Basic
    @Column(name = "surnamew", nullable = true, length = 40)
    public String getSurnamew() {
        return surnamew;
    }

    public void setSurnamew(String surnamew) {
        this.surnamew = surnamew;
    }

    @Basic
    @Column(name = "namew", nullable = true, length = 40)
    public String getNamew() {
        return namew;
    }

    public void setNamew(String namew) {
        this.namew = namew;
    }

    @Basic
    @Column(name = "fathnamew", nullable = true, length = 40)
    public String getFathnamew() {
        return fathnamew;
    }

    public void setFathnamew(String fathnamew) {
        this.fathnamew = fathnamew;
    }

    @Basic
    @Column(name = "telephone", nullable = true, length = 16)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "loginW", nullable = true, length = 20)
    public String getLoginW() {
        return loginW;
    }

    public void setLoginW(String loginW) {
        this.loginW = loginW;
    }

    @Basic
    @Column(name = "passwordW", nullable = true, length = 30)
    public String getPasswordW() {
        return passwordW;
    }

    public void setPasswordW(String passwordW) {
        this.passwordW = passwordW;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkerEntity that = (WorkerEntity) o;

        if (idworker != that.idworker) return false;
        if (surnamew != null ? !surnamew.equals(that.surnamew) : that.surnamew != null) return false;
        if (namew != null ? !namew.equals(that.namew) : that.namew != null) return false;
        if (fathnamew != null ? !fathnamew.equals(that.fathnamew) : that.fathnamew != null) return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null) return false;
        if (loginW != null ? !loginW.equals(that.loginW) : that.loginW != null) return false;
        if (passwordW != null ? !passwordW.equals(that.passwordW) : that.passwordW != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idworker;
        result = 31 * result + (surnamew != null ? surnamew.hashCode() : 0);
        result = 31 * result + (namew != null ? namew.hashCode() : 0);
        result = 31 * result + (fathnamew != null ? fathnamew.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (loginW != null ? loginW.hashCode() : 0);
        result = 31 * result + (passwordW != null ? passwordW.hashCode() : 0);
        return result;
    }
}
