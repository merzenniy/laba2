package model;

import javax.persistence.*;

@Entity
@Table(name = "counter", schema = "var4", catalog = "")
public class CounterEntity {
    private int idcounter;
    private String codecounter;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idcounter", nullable = false)
    public int getIdcounter() {
        return idcounter;
    }

    public void setIdcounter(int idcounter) {
        this.idcounter = idcounter;
    }

    @Basic
    @Column(name = "codecounter", nullable = true, length = 10)
    public String getCodecounter() {
        return codecounter;
    }

    public void setCodecounter(String codecounter) {
        this.codecounter = codecounter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CounterEntity that = (CounterEntity) o;

        if (idcounter != that.idcounter) return false;
        if (codecounter != null ? !codecounter.equals(that.codecounter) : that.codecounter != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idcounter;
        result = 31 * result + (codecounter != null ? codecounter.hashCode() : 0);
        return result;
    }
}
