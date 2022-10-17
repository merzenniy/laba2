package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AbonentEntity;
import model.CounterValueEntity;
import model.TarifEntity;
import model.WorkerEntity;
import session.AbonentSession;
import util.NewWindow;

import javax.persistence.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AbonentOneController implements Initializable {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("default");

    @FXML
    private ChoiceBox<String> tarifEntityChoiceBox;
    @FXML
    private Label sLabel;
    @FXML
    private Label nLabel;
    @FXML
    private Label fLabel;
    @FXML
    private Label tLabel;

    @FXML
    private TableView tableView;
    private ObservableList workers;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sLabel.setText(AbonentSession.getInstance().getAbonent().getSurnameab());
        nLabel.setText(AbonentSession.getInstance().getAbonent().getNameab());
        fLabel.setText(AbonentSession.getInstance().getAbonent().getFathnameab());
        tLabel.setText(AbonentSession.getInstance().getAbonent().getTarifByIdtarif().getNametarif());
        tarifEntityChoiceBox.getItems().addAll(getTariffList());
        updateTable();
        setupTable();

    }


    private List<String> getTariffList() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        Query q = em.createQuery("SELECT t FROM TarifEntity t ");
        List<TarifEntity> abonents = q.getResultList();
        List<String> tariffNames = new ArrayList<>();
        for (TarifEntity t : abonents) {
            tariffNames.add(t.getNametarif());
        }
        em.close();
        return tariffNames;
    }

    public void add(ActionEvent actionEvent) throws IOException {
        NewWindow.setNewWindow("addPokazanie.fxml", actionEvent);
    }

    public void changeTariff(ActionEvent actionEvent) throws IOException {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            Query query = em.createQuery("update AbonentEntity a set a.tarifByIdtarif =: tariff where a.idabonent=:idabonent").setParameter("tariff", getChosenTariff(em, tarifEntityChoiceBox.getValue())).setParameter("idabonent", AbonentSession.getInstance().getAbonent().getIdabonent());
            query.executeUpdate();
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction != null)
                entityTransaction.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        NewWindow.setNewWindow("abonentOne.fxml", actionEvent);
    }


    private void updateTable() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
/*
        Query q = em.createQuery("select c from CounterValueEntity c inner join AbonentEntity a on c.abonentByIdAbonent.idabonent = a.idabonent  where c.abonentByIdAbonent.idabonent=: idAbonent").setParameter("idAbonent", AbonentSession.getInstance().getAbonent().getIdabonent());
*/
        Query q = em.createQuery("select c from CounterValueEntity c where c.abonentByIdAbonent.idabonent=: idAbonent").setParameter("idAbonent", AbonentSession.getInstance().getAbonent().getIdabonent());

        List results = q.getResultList();
        if (workers == null) {
            workers = FXCollections.observableArrayList(results);
        } else {
            workers.clear();
            workers.addAll(results);
        }
        em.close();
    }

    private void setupTable() {
        TableColumn<CounterValueEntity, BigDecimal> fTableColumn = new TableColumn<>();
        fTableColumn.setText("Показания");
        fTableColumn.setMinWidth(80);
        fTableColumn.setCellValueFactory(new PropertyValueFactory<>("valueamount"));

        TableColumn<CounterValueEntity, Date> startdateTableColumn = new TableColumn<>();
        startdateTableColumn.setText("Дата");
        startdateTableColumn.setMinWidth(72);
        startdateTableColumn.setCellValueFactory(new PropertyValueFactory<>("datetaking"));

        tableView.getColumns().addAll(fTableColumn, startdateTableColumn);
        tableView.setItems(workers);


    }

    private TarifEntity getChosenTariff(EntityManager entityManager, String name) {
        return entityManager.createQuery("select t from TarifEntity t where t.nametarif =: name", TarifEntity.class).setParameter("name", name).getSingleResult();
    }
}
