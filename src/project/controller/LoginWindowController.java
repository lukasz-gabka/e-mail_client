package project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.EmailManager;
import project.view.ViewFactory;

public class LoginWindowController extends BaseController {

    public LoginWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }

    @FXML
    private TextField emailAddressField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    void loginButtonAction() {
        viewFactory.showMainWindow();
        Stage stage = (Stage)errorLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
}
