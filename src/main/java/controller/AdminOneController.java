package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.WorkerEntity;
import util.NewWindow;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminOneController implements Initializable {
    @FXML
    private TableView<WorkerEntity> tableView;

    private ObservableList workers;

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("default");

    @Override
    public void initialize(URL url, ResourceBundle rb){
        updateTable();
        setupTable();

    }

    public void addUser(ActionEvent event) throws IOException {
        NewWindow.setNewWindow("addWorker.fxml", event);
    }

    public void checkLogs(ActionEvent event) throws IOException {
        NewWindow.setNewWindow("checkLogsMenu.fxml", event);
    }

    private void updateTable(){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        Query q = em.createQuery("SELECT w FROM WorkerEntity w");
        List results = q.getResultList();
        if(workers == null){
            workers = FXCollections.observableArrayList(results);
        }
        else{
            workers.clear();
            workers.addAll(results);
        }
        em.close();
    }

    private void setupTable(){
        TableColumn<WorkerEntity, String> fTableColumn = new TableColumn<>();
        fTableColumn.setText("Прізвище");
        fTableColumn.setMinWidth(100);
        fTableColumn.setCellValueFactory(new PropertyValueFactory<>("surnamew"));

        TableColumn<WorkerEntity, String> iTableColumn = new TableColumn<>();
        iTableColumn.setText("Ім'я");
        iTableColumn.setMinWidth(100);
        iTableColumn.setCellValueFactory(new PropertyValueFactory<>("namew"));

        TableColumn<WorkerEntity, String> oTableColumn = new TableColumn<>();
        oTableColumn.setText("По батькові");
        oTableColumn.setMinWidth(100);
        oTableColumn.setCellValueFactory(new PropertyValueFactory<>("fathnamew"));



        TableColumn<WorkerEntity, String> salaryTableColumn = new TableColumn<>();
        salaryTableColumn.setText("Телефон");
        salaryTableColumn.setMinWidth(50);
        salaryTableColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));



        tableView.getColumns().addAll(fTableColumn, iTableColumn, oTableColumn, salaryTableColumn);
        tableView.setItems(workers);



    }
}
