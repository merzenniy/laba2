package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.CounterValueEntity;
import model.WorkerEntity;
import session.AbonentSession;
import util.NewWindow;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;


public class AddPokazanieController {
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField pokTF;

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("default");

    public void add(ActionEvent actionEvent) throws IOException {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            CounterValueEntity counterValue = new CounterValueEntity();
            counterValue.setAbonentByIdAbonent(AbonentSession.getInstance().getAbonent());
            counterValue.setDatetaking(Date.valueOf(datePicker.getValue()));
            counterValue.setValueamount(BigDecimal.valueOf(Double.parseDouble(pokTF.getText())));
            em.persist(counterValue);
            et.commit();
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        NewWindow.setNewWindow("abonentOne.fxml", actionEvent);





    }
    public void back(ActionEvent actionEvent) throws IOException {
        NewWindow.setNewWindow("abonentOne.fxml", actionEvent);
    }




}
