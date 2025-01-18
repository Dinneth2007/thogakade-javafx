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
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {

    public Label lblDate;
    public JFXComboBox cmbCustId;
    public JFXComboBox cmbItemcode;
    public TextField txtSalary;
    public TextField txtOrderId;
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
    ObservableList<CartTableModel> CartList=FXCollections.observableArrayList();
    OrderController controller;
    CustomerController customerController;
    ArrayList<OrderDetail>orderDetailsList;
    @FXML
    void BtnOnActionPlaceOrder(ActionEvent event) throws SQLException {
        Order order=new Order(txtOrderId.getText(),"2025-01-18",cmbCustId.getValue().toString(),orderDetailsList);
        if(controller.addOrder(order)){
            System.out.println("Order Added Sucessfuly!");
            if(ItemController.updateItemStock(order.getOrderDetailList())){
                System.out.println("Item Table updated!!");

            }
        }
        CartList.clear();
        orderDetailsList.clear();
    }

    @FXML
    void BtnOnAddToCart(ActionEvent event) {
       // loadTable();
        String code=cmbItemcode.getValue().toString();
        String desc=txtItemDesc.getText();
        int Qty=Integer.parseInt(txtQty.getText());
        double unitprice=Double.parseDouble(txtUnitPrice.getText());
        double total=unitprice*Qty;
        CartList.add(new CartTableModel(code,desc,Qty,unitprice,total));
        ItemTable.setItems(CartList);
        orderDetailsList.add(new OrderDetail(txtOrderId.getText(),cmbItemcode.getValue().toString(),Integer.parseInt(txtQty.getText()),Double.parseDouble(txtUnitPrice.getText())));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
       colDesc.setCellValueFactory(new PropertyValueFactory<>("Desc"));
        colQOH.setCellValueFactory(new PropertyValueFactory<>("QTY"));
        colUP.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        orderDetailsList=new ArrayList<>();
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
            lblOrderId.setText(controller.loadNewOrderId());
            loadCustomerIds();
            loadItemIds();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
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
    public void loadItemIds() throws SQLException {

        ObservableList<String> itemCode= FXCollections.observableArrayList();
        List<Item> itemlist=ItemController.getItems();
        itemlist.forEach(item -> {
            itemCode.add(item.getItemcode());
        });
        cmbItemcode.setItems(itemCode);
    }

    public void cmbCustIdOnAction(ActionEvent actionEvent) throws SQLException {
        Customer customer=customerController.serachById(cmbCustId.getValue().toString());
        txtName.setText(customer.getName());
        txtxAddress.setText(customer.getAddress());
        txtSalary.setText(String.valueOf(customer.getSalary()));
    }

    public void itemdropOnAction(ActionEvent actionEvent) throws SQLException {
        Item item=ItemController.serachById(cmbItemcode.getValue().toString());
       txtItemDesc.setText(item.getDesc());
        txtStock.setText(String.valueOf(item.getQtyOnHand()));
        txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
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
