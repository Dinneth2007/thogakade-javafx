package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerViewController implements Initializable {
    public TextField IDtxt;
    public TextField Nametxt;
    public TextField Adresstxt;
    public TextField Salarytxt;
    public ComboBox titledropdown;
    public TableView table;
    public TableColumn id;
    public TableColumn name;
    public TableColumn address;
    public TableColumn salary;
    public Button BtnDlt;

    private CustomerController controller;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //PropertyBinding for tables columns
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        controller=new CustomerController();
        loadTitles();
    }

    public void add(ActionEvent actionEvent) {
        try {
            System.out.println(controller.addcustomer(new Customer(IDtxt.getText(),titledropdown.getPromptText()+" "+Nametxt.getText(),Adresstxt.getText(),Double.parseDouble(Salarytxt.getText()))));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void loadTitles(){
        ObservableList<String> titleLists= FXCollections.observableArrayList();
        titleLists.add("Mr");
        titleLists.add("Mrs");
        titleLists.add("Dr");
        titledropdown.setItems(titleLists);
    }
    public void loadTable(){
        ObservableList<Customer> customerObsList=FXCollections.observableArrayList();
        try {
            controller.getCustomers().forEach(customer -> {
                customerObsList.add(customer);
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        table.setItems(customerObsList);
    }

    public void getCustomers(ActionEvent actionEvent) {
        loadTable();
    }

    public void navigate(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();

        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/RegisterView.fxml"))));
        stage.show();


    }

    public void BtnOnActionDlt(ActionEvent actionEvent) throws SQLException {
        if(controller.deleteCustomer(id.getText())){
            System.out.println("dlt sucess");
        };
    }
}
