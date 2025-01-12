package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class DashBoardViewController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button customerFromBtn;

    @FXML
    void LoadCustomerForm(ActionEvent event) throws IOException {
        URL resource=this.getClass().getResource("/view/RegisterView.fxml");
        assert resource!= null;
        Parent load= FXMLLoader.load(resource);
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(load);

    }

}
