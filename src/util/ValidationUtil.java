package util;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.TextField;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ValidationUtil {
    public static Object validate(LinkedHashMap<TextField,Pattern>hashMap, JFXButton button) {
        button.setDisable(true);
        for (TextField textFieldKey : hashMap.keySet()) {
            Pattern pattern = hashMap.get(textFieldKey);
            if (!pattern.matcher(textFieldKey.getText()).matches()) {

                if (!textFieldKey.getText().isEmpty()) {
                    textFieldKey.getParent().setStyle("-fx-border-color: red");
                }

                return textFieldKey;
            }
            textFieldKey.getParent().setStyle("-fx-border-color: green");
        }
        button.setDisable(false);
        return true;
    }
}
