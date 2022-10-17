package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.WorkerEntity;
import util.NewWindow;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.Random;

public class AddWorkerController {
    @FXML
    private TextField fTextField;

    @FXML
    private TextField iTextField;

    @FXML
    private TextField oTextField;

    @FXML
    private TextField phoneTextfield;

    @FXML
    private TextField loginTextField;

    private Stage stage;
    private Parent root;

    private String f;
    private String i;
    private String o;
    private String telephone;
    private String login;

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("default");

    public void addNewUser(ActionEvent event) throws IOException {
        f = fTextField.getText();
        i = iTextField.getText();
        o = oTextField.getText();
        telephone = phoneTextfield.getText();
        login = loginTextField.getText();
        addWorker(f, i, o, telephone, login);
        ENTITY_MANAGER_FACTORY.close();
        NewWindow.setNewWindow("adminOne.fxml", event);
    }

    private void addWorker(String f, String i, String o, String telephone, String login) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            WorkerEntity worker = new WorkerEntity();
            worker.setSurnamew(f);
            worker.setNamew(i);
            worker.setFathnamew(o);
            worker.setTelephone(telephone);
            worker.setLoginW(login);
            worker.setPasswordW(generatePassword());
            em.persist(worker);
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
    private String generatePassword(){
        return new Random().ints(10, 33, 122).collect(StringBuilder::new,
                        StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
