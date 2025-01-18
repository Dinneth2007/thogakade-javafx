package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {

    @FXML
    private Button loginBtn;

    @FXML
    private TextField txtemail;

    @FXML
    private PasswordField txtpwd;

    @FXML
    void BtnOnActionLogin(ActionEvent event) throws SQLException, IOException {
        System.out.println(controller.login(txtemail.getText(),txtpwd.getText()));
        if(controller.login(txtemail.getText(),txtpwd.getText())){
            Stage stage=new Stage();

            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/CustomerView.fxml"))));
            stage.show();
            Stage stage2=new Stage();

            stage2.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddOrderForm.fxml"))));
            stage2.show();
        }else{
            new Alert(Alert.AlertType.ERROR,"wrong credentials").show();

        }
    }
    AuthController controller;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            controller=new AuthController();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
