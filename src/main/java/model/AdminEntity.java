package model;

import javax.persistence.*;

@Entity
@Table(name = "admin", schema = "var4", catalog = "")
public class AdminEntity {
    private int idAdmin;
    private String loginAdmin;
    private String passAdmin;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idAdmin", nullable = false)
    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    @Basic
    @Column(name = "loginAdmin", nullable = true, length = 45)
    public String getLoginAdmin() {
        return loginAdmin;
    }

    public void setLoginAdmin(String loginAdmin) {
        this.loginAdmin = loginAdmin;
    }

    @Basic
    @Column(name = "passAdmin", nullable = true, length = 40)
    public String getPassAdmin() {
        return passAdmin;
    }

    public void setPassAdmin(String passAdmin) {
        this.passAdmin = passAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminEntity that = (AdminEntity) o;

        if (idAdmin != that.idAdmin) return false;
        if (loginAdmin != null ? !loginAdmin.equals(that.loginAdmin) : that.loginAdmin != null) return false;
        if (passAdmin != null ? !passAdmin.equals(that.passAdmin) : that.passAdmin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAdmin;
        result = 31 * result + (loginAdmin != null ? loginAdmin.hashCode() : 0);
        result = 31 * result + (passAdmin != null ? passAdmin.hashCode() : 0);
        return result;
    }
}
