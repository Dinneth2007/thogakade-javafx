package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {

    public Label lblDate;
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

    @FXML
    void BtnOnActionPlaceOrder(ActionEvent event) {

    }

    @FXML
    void BtnOnAddToCart(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Date date= new Date();
        lblDate.setText(date.toString());
    }
}
