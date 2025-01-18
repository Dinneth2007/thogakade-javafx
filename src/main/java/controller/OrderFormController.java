package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Customer;
import model.Item;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {

    public Label lblDate;
    public JFXComboBox cmbCustId;
    public JFXComboBox cmbItemcode;
    public TextField txtSalary;
    @FXML
    private JFXButton BtnAddtoCart;

    @FXML
    private JFXButton BtnAddtoCart1;

    @FXML
    private TableView ItemTable;

    @FXML
    private TableColumn colDesc;

    @FXML
    private TableColumn colItemCode;

    @FXML
    private TableColumn colQOH;

    @FXML
    private TableColumn colTotal;

    @FXML
    private TableColumn colUP;

    @FXML
    private Label lblOrderId;

    @FXML
    private TextField txtItemDesc;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtStock;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private TextField txtxAddress;
    OrderController controller;
    CustomerController customerController;
    @FXML
    void BtnOnActionPlaceOrder(ActionEvent event) {

    }

    @FXML
    void BtnOnAddToCart(ActionEvent event) {
       // loadTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            customerController=new CustomerController();
            controller=new OrderController();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Date date= new Date();
        lblDate.setText(date.toString());
        try {
            loadCustomerIds();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadCustomerIds() throws SQLException {
        CustomerController customerController=new CustomerController();
        ObservableList<String> customerId= FXCollections.observableArrayList();
        List<Customer> customerlist=customerController.getCustomers();
        customerlist.forEach(customer -> {
            customerId.add(customer.getId());
        });
        cmbCustId.setItems(customerId);
    }

    public void cmbCustIdOnAction(ActionEvent actionEvent) throws SQLException {
        Customer customer=customerController.serachById(cmbCustId.getValue().toString());
        txtName.setText(customer.getName());
        txtxAddress.setText(customer.getAddress());
        txtSalary.setText(String.valueOf(customer.getSalary()));
    }
//    public void loadTable(){
//        ObservableList<Item> ItemObsList=FXCollections.observableArrayList();
//        try {
//            ItemController.getItems().forEach(item -> {
//                ItemObsList.add(item);
//            });
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        ItemTable.setItems(ItemObsList);
//    }
}
