package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.AbonentEntity;
import model.LogEntity;
import model.WorkerEntity;
import session.AbonentSession;
import session.WorkerSession;
import util.NewWindow;

import javax.persistence.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class StartController {
    @FXML
    private TextField logintf;
    @FXML
    private TextField passwordtf;
    @FXML
    private RadioButton adminButton;
    @FXML
    private RadioButton workerButton;
    @FXML
    private RadioButton abonentButton;


    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("default");

    public void login(ActionEvent event) throws IOException {

        if (adminButton.isSelected() && passwordtf.getText().equals(getAdminPassword())) {
            ENTITY_MANAGER_FACTORY.close();
            NewWindow.setNewWindow("adminOne.fxml", event);
        }else
        if (workerButton.isSelected() && passwordtf.getText().equals(getWorkerPassword())) {
            addLoginLog(logintf.getText());
            ENTITY_MANAGER_FACTORY.close();
            NewWindow.setNewWindow("workerOne.fxml", event);
        }else
        if (abonentButton.isSelected() && passwordtf.getText().equals(getAbonentPassword())) {
            EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
            TypedQuery<AbonentEntity> tq = em.createQuery("select a from AbonentEntity a where a.loginA=: login and  a.passwordA =: password", AbonentEntity.class).setParameter("login", logintf.getText()).setParameter("password", passwordtf.getText());
            AbonentSession.getInstance(tq.getSingleResult());
            em.close();
            ENTITY_MANAGER_FACTORY.close();
            NewWindow.setNewWindow("abonentOne.fxml", event);
        }
    }

    private String getAbonentPassword() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        Query q = em.createQuery("select w.passwordA from AbonentEntity w where w.loginA =: login");
        q.setParameter("login", logintf.getText());
        String result = null;
        try {
            result = (String) q.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        System.out.println(result);
        return result;
    }

    private String getWorkerPassword() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        Query q = em.createQuery("select w.passwordW from WorkerEntity w where w.loginW =: login");
        q.setParameter("login", logintf.getText());
        String result = null;
        try {
            result = (String) q.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        System.out.println(result);
        return result;
    }

    private String getAdminPassword() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        Query q = em.createQuery("select w.passAdmin from AdminEntity w where w.loginAdmin =: login");
        q.setParameter("login", logintf.getText());
        String result = null;
        try {
            result = (String) q.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        System.out.println(result);
        return result;
    }
    private void addLoginLog(String login) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        String query = "SELECT w FROM WorkerEntity w WHERE w.loginW=:login";
        TypedQuery<WorkerEntity> tq = em.createQuery(query, WorkerEntity.class);
        tq.setParameter("login", login);
        try {
            et = em.getTransaction();
            et.begin();
            LogEntity log = new LogEntity();
            log.setTime(Timestamp.valueOf(LocalDateTime.now()));
            log.setActivity("Вошёл в систему");
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