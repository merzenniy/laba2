package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.LogEntity;
import model.WorkerEntity;
import util.NewWindow;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CheckLogsMenuController implements Initializable {

    @FXML
    private TableView tableView;

    @FXML
    private ChoiceBox<String> choiceBox;

    private Stage stage;
    private Parent root;

    private ObservableList<LogEntity> logs;

    private Boolean c = false;

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("default");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll(getWorkerList());
        choiceBox.setOnAction(this::getChosenWorker);
    }

    public void back(ActionEvent event) throws IOException {
        ENTITY_MANAGER_FACTORY.close();
        NewWindow.setNewWindow("adminOne.fxml", event);
    }

    private List<String> getWorkerList(){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        Query q = em.createQuery("SELECT w FROM WorkerEntity w ");
        List<WorkerEntity> workers = q.getResultList();
        List<String> workerNames = new ArrayList<>();
        for (WorkerEntity w: workers) {
            workerNames.add(w.toString());
        }
        em.close();
        return workerNames;
    }

    private void getChosenWorker(ActionEvent event){
        String worker = choiceBox.getValue();
        updateTable(getChosenWorkerF(worker), getChosenWorkerI(worker));
        setupTable();
        c = true;
    }

    private void updateTable(String f, String i){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        Query q = em.createQuery("SELECT l FROM LogEntity l INNER JOIN WorkerEntity w on l.workerByIdWorker.idworker = w.idworker WHERE w.surnamew=:f and  w.namew=:i");
        q.setParameter("f", f);
        q.setParameter("i", i);
        List results = q.getResultList();
        if(logs == null){
            logs = FXCollections.observableArrayList(results);
        }
        else{
            logs.clear();
            logs.addAll(results);
        }
        em.close();
    }

    private void setupTable(){
        if(!c) {
            TableColumn<LogEntity, Timestamp> dateTableColumn = new TableColumn<>();
            dateTableColumn.setText("Час дії");
            dateTableColumn.setMinWidth(100);
            dateTableColumn.setCellValueFactory(new PropertyValueFactory<>("Time"));

            TableColumn<LogEntity, String> activityTableColumn = new TableColumn<>();
            activityTableColumn.setText("Опис дії");
            activityTableColumn.setMinWidth(100);
            activityTableColumn.setCellValueFactory(new PropertyValueFactory<>("Activity"));

            tableView.getColumns().addAll(dateTableColumn, activityTableColumn);
        }
        tableView.setItems(logs);
    }

    private String getChosenWorkerF(String fi){
        return fi.substring(0, fi.indexOf(" "));
    }

    private String getChosenWorkerI(String fi){
        return fi.substring(fi.indexOf(" ") + 1);
    }
}