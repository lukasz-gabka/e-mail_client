package project.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.EmailManager;
import project.controller.BaseController;
import project.controller.LoginWindowController;
import project.controller.MainWindowController;
import project.controller.OptionsWindowController;

import java.io.IOException;

public class ViewFactory {

    private EmailManager emailManager;

    private ColorTheme colorTheme = ColorTheme.DEFAULT;
    private FontSize fontSize = FontSize.MEDIUM;

    public ViewFactory(EmailManager emailManager) {
        this.emailManager = emailManager;
    }

    public void showLoginWindow() {
        BaseController controller = new LoginWindowController(emailManager, this, "LoginWindow.fxml");
        initializeStage(controller);
    }

    public void showMainWindow() {
        BaseController controller = new MainWindowController(emailManager, this, "MainWindow.fxml");
        initializeStage(controller);
    }

    public void showOptionsWindow() {
        BaseController controller = new OptionsWindowController(emailManager, this, "OptionsWindow.fxml");
        initializeStage(controller);
    }

    private void initializeStage(BaseController baseController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(baseController.getFxmlName()));
        fxmlLoader.setController(baseController);

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

    public void closeStage(Stage stageToClose) {
        stageToClose.close();
    }

    public ColorTheme getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(ColorTheme colorTheme) {
        this.colorTheme = colorTheme;
    }

    public FontSize getFontSize() {
        return fontSize;
    }

    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }
}
