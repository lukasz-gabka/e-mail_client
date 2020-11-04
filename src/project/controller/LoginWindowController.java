package project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.EmailManager;
import project.controller.services.LoginService;
import project.model.EmailAccount;
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
        if (fieldsAreValid()) {
            EmailAccount emailAccount = new EmailAccount(emailAddressField.getText(), passwordField.getText());
            LoginService loginService = new LoginService(emailAccount, emailManager);

            EmailLoginResult emailLoginResult = loginService.login();

            switch (emailLoginResult) {
                case SUCCESS:
                    System.out.println("Sukces" + emailAccount);
                    return;
            }
        }

        viewFactory.showMainWindow();
        Stage stage = (Stage)errorLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    private boolean fieldsAreValid() {
        if (emailAddressField.getText().isEmpty()) {
            errorLabel.setText("Podaj adres e-mail");
            return false;
        }

        if (passwordField.getText().isEmpty()) {
            errorLabel.setText("Podaj hasło");
            return false;
        }

        return true;
    }
}
