package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;
import session.WorkerSession;
import util.NewWindow;

import javax.persistence.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class WorkerOneController implements Initializable {
    @FXML
    TextField losstf;
    @FXML
    TextField typetf;
    @FXML
    TextField descriptiontf;
    @FXML
    DatePicker datetf;
    @FXML
    TextField adresstf;
    @FXML
    ChoiceBox<String> listtf;

    private Stage stage;
    private Parent root;

    private BigDecimal loss;
    private String type;
    private String description;
    private LocalDate date;
    private String adress;

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("default");

    public void addNewAccident(ActionEvent event) throws IOException {
        addLoginLog();
        loss = BigDecimal.valueOf(Double.parseDouble(losstf.getText()));
        type = typetf.getText();
        description = descriptiontf.getText();
        adress = adresstf.getText();
        date = datetf.getValue();
        addWorkerAccident(adress, date, description, loss, type);
        ENTITY_MANAGER_FACTORY.close();
    }

    private void addWorkerAccident(String adress, LocalDate date, String description, BigDecimal loss, String type) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            AccidentEntity accident = new AccidentEntity();
            accident.setAddressac(adress);
            accident.setDateac(java.sql.Date.valueOf(date));
            accident.setDesription(description);
            accident.setLoss(loss);
            accident.setAbonentByIdabonent(getChosenAbonent());
            em.persist(accident);
            et.commit();
            et.begin();
            AccidentworkerEntity accidentworker = new AccidentworkerEntity();
            accidentworker.setTypework(type);
            accidentworker.setAccidentByIdaccident(accident);
            accidentworker.setWorkerByIdworker(WorkerSession.getInstance().getWorker());
            em.persist(accidentworker);
            et.commit();
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listtf.getItems().addAll(getAbonentList());
    }

    public void back(ActionEvent event) throws IOException {
        ENTITY_MANAGER_FACTORY.close();
        NewWindow.setNewWindow("adminOne.fxml", event);
    }

    private List<String> getAbonentList(){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        Query q = em.createQuery("SELECT w FROM AbonentEntity w ");
        List<AbonentEntity> workers = q.getResultList();
        List<String> workerNames = new ArrayList<>();
        for (AbonentEntity w: workers) {
            workerNames.add(w.toString());
        }
        em.close();
        return workerNames;
    }
    private AbonentEntity getChosenAbonent(){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        TypedQuery<AbonentEntity> q = em.createQuery("SELECT a FROM AbonentEntity a where a.surnameab =: surname and a.nameab =: name", AbonentEntity.class).setParameter("surname", getChosenAbonentF(listtf.getValue())).setParameter("name", getChosenAbonentI(listtf.getValue()));
        return q.getSingleResult();
    }
    private String getChosenAbonentF(String fi){
        return fi.substring(0, fi.indexOf(" "));
    }
    private String getChosenAbonentI(String fi){
        return fi.substring(fi.indexOf(" ") + 1);
    }

    private void addLoginLog() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        String query = "SELECT w FROM WorkerEntity w WHERE w.idworker=:idworker";
        TypedQuery<WorkerEntity> tq = em.createQuery(query, WorkerEntity.class);
        tq.setParameter("idworker",WorkerSession.getInstance().getWorker().getIdworker() );
        try {
            et = em.getTransaction();
            et.begin();
            LogEntity log = new LogEntity();
            log.setTime(Timestamp.valueOf(LocalDateTime.now()));
            log.setActivity("Выполнил работу");
            log.setWorkerByIdWorker(tq.getSingleResult());
            WorkerSession.getInstance(tq.getSingleResult());
            em.persist(log);
            et.commit();
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

}
