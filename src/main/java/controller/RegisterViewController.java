package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jasypt.util.text.BasicTextEncryptor;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterViewController implements Initializable {
    AuthController controller;
    @FXML
    private Button redBtn;

    @FXML
    private TextField txtUname;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtpwd;

    @FXML
    private TextField txtrepwd;

    @FXML
    void register(ActionEvent event) {

        try {

            if(controller.addregister(txtUname.getText(),txtemail.getText(),txtpwd.getText(),txtrepwd.getText())){
                new Alert(Alert.AlertType.CONFIRMATION,"registered!").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Passwords are not the same").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            controller=new AuthController();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void navigatetologin(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();

        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/loginView.fxml"))));
        stage.show();
    }
}
