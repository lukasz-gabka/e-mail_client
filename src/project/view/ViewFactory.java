package project.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.EmailManager;
import project.controller.BaseController;
import project.controller.LoginWindowController;

import java.io.IOException;

public class ViewFactory {

    private EmailManager emailManager;

    public ViewFactory(EmailManager emailManager) {
        this.emailManager = emailManager;
    }

    public void showLoginWindow() {
        System.out.println("Showing login window");

        BaseController controller = new LoginWindowController(emailManager, this, "LoginWindow.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(controller.getFxmlName()));
        fxmlLoader.setController(controller);

        Parent parent;

        try {
            parent = fxmlLoader.load();
        } catch(IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.show();

    }
}
