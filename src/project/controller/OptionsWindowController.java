package project.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.text.Font;
import javafx.util.StringConverter;
import project.EmailManager;
import project.view.ColorTheme;
import project.view.FontSize;
import project.view.ViewFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class OptionsWindowController extends BaseController implements Initializable {

    public OptionsWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }

    @FXML
    private Slider fontSizePicker;

    @FXML
    private ChoiceBox<ColorTheme> themePicker;

    @FXML
    void applyButtonAction() {

    }

    @FXML
    void cancelButtonAction() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpThemePicker();
        setUpSizePicker();
    }

    private void setUpSizePicker() {
        fontSizePicker.setMin(0);
        fontSizePicker.setMax(FontSize.values().length - 1);
        fontSizePicker.setValue(viewFactory.getFontSize().ordinal());

        fontSizePicker.setMajorTickUnit(1);
        fontSizePicker.setMinorTickCount(0);
        fontSizePicker.setBlockIncrement(1);
        fontSizePicker.setSnapToTicks(true);
        fontSizePicker.setShowTickMarks(true);
        fontSizePicker.setShowTickLabels(true);
        fontSizePicker.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double aDouble) {
                int i = aDouble.intValue();
                return FontSize.values()[i].toString();
            }

            @Override
            public Double fromString(String s) {
                return null;
            }
        });
        fontSizePicker.valueProperty().addListener((obs, oldVal, newVal) -> {
            fontSizePicker.setValue(newVal.intValue());
        });
    }

    private void setUpThemePicker() {
        themePicker.setItems(FXCollections.observableArrayList(ColorTheme.values()));
        themePicker.setValue(viewFactory.getColorTheme());
    }
}
