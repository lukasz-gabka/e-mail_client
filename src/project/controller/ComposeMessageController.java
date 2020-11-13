package project.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import project.EmailManager;
import project.controller.services.EmailSenderService;
import project.model.EmailAccount;
import project.view.ViewFactory;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ComposeMessageController extends BaseController implements Initializable {

    private List<File> attachments = new ArrayList<File>();

    @FXML
    private TextField recipientTextField;

    @FXML
    private TextField subjectTextField;

    @FXML
    private HTMLEditor htmlEditor;

    @FXML
    private Label errorLabel;

    @FXML
    private ChoiceBox<EmailAccount> emailAccountChoice;

    public ComposeMessageController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }

    @FXML
    void sendButtonAction() {
        EmailSenderService emailSenderService = new EmailSenderService(
                emailAccountChoice.getValue(),
                subjectTextField.getText(),
                recipientTextField.getText(),
                htmlEditor.getHtmlText(),
                attachments
        );
        emailSenderService.start();
        emailSenderService.setOnSucceeded(e -> {
            EmailSendingResult emailSendingResult = emailSenderService.getValue();

            switch (emailSendingResult) {
                case SUCCESS:
                    Stage stage = (Stage) errorLabel.getScene().getWindow();
                    viewFactory.closeStage(stage);
                    break;
                case FAILED_BY_PROVIDER:
                    errorLabel.setText("Błąd dostawcy usługi e-mail");
                    break;
                case FAILED_BY_UNEXPECTED_ERROR:
                    errorLabel.setText("Nieznany błąd");
                    break;
                default:
                    break;
            }
        });
    }

    @FXML
    void attachButtonAction() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            attachments.add(selectedFile);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emailAccountChoice.setItems(emailManager.getEmailAccounts());
        emailAccountChoice.setValue(emailManager.getEmailAccounts().get(0));
    }
}
