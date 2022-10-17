package model;

import javax.persistence.*;

@Entity
@Table(name = "accidentworker", schema = "var4", catalog = "")
public class AccidentworkerEntity {
    private int idaccwork;
    private String typework;
    private WorkerEntity workerByIdworker;
    private AccidentEntity accidentByIdaccident;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idaccwork", nullable = false)
    public int getIdaccwork() {
        return idaccwork;
    }

    public void setIdaccwork(int idaccwork) {
        this.idaccwork = idaccwork;
    }

    @Basic
    @Column(name = "typework", nullable = true, length = 50)
    public String getTypework() {
        return typework;
    }

    public void setTypework(String typework) {
        this.typework = typework;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccidentworkerEntity that = (AccidentworkerEntity) o;

        if (idaccwork != that.idaccwork) return false;
        if (typework != null ? !typework.equals(that.typework) : that.typework != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idaccwork;
        result = 31 * result + (typework != null ? typework.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idworker", referencedColumnName = "idworker", nullable = false)
    public WorkerEntity getWorkerByIdworker() {
        return workerByIdworker;
    }

    public void setWorkerByIdworker(WorkerEntity workerByIdworker) {
        this.workerByIdworker = workerByIdworker;
    }

    @ManyToOne
    @JoinColumn(name = "idaccident", referencedColumnName = "idaccident", nullable = false)
    public AccidentEntity getAccidentByIdaccident() {
        return accidentByIdaccident;
    }

    public void setAccidentByIdaccident(AccidentEntity accidentByIdaccident) {
        this.accidentByIdaccident = accidentByIdaccident;
    }
}
