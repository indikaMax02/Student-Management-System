package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashBoardFormController {
    public ImageView close;
    public StackPane dashboardStackPane;
    public Pane regiterPane;
    public Pane dashBoardPane;
    public Pane manageCoursePane;
    public Pane manageStudentPane;

    public void initialize(){





    }


    public void closeOnMouseClicked(MouseEvent mouseEvent) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
    public void clearPaneColor(){
        regiterPane.setStyle(null);
        dashBoardPane.setStyle(null);
        manageCoursePane.setStyle(null);
        manageStudentPane.setStyle(null);
    }




    public void registerOnAction(MouseEvent mouseEvent) throws IOException {
        clearPaneColor();
        regiterPane.setStyle("-fx-background-color: linear-gradient(to left top, #dcbcbc, #EE75FE)");
        URL resource = getClass().getResource("../view/RegistorStudentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        dashboardStackPane.getChildren().clear();
        dashboardStackPane.getChildren().add(load);

       // regiterPane.setStyle("-fx-background-radius: 15px");
       // regiterPane.setStyle("-fx-border-radius: 15px");

    }

    public void ManageCourseOnAction(MouseEvent mouseEvent) throws IOException {
        clearPaneColor();
        manageCoursePane.setStyle("-fx-background-color: linear-gradient(to left top, #dcbcbc, #EE75FE)");
         URL resource = getClass().getResource("../view/ManageCourseForm.fxml");
         Parent load = FXMLLoader.load(resource);
        dashboardStackPane.getChildren().clear();
        dashboardStackPane.getChildren().add(load);

    }

    public void manageStudentOnAction(MouseEvent mouseEvent) throws IOException {
        clearPaneColor();
        manageStudentPane.setStyle("-fx-background-color: linear-gradient(to left top, #dcbcbc, #EE75FE)");
        URL resource = getClass().getResource("../view/ManageStudentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        dashboardStackPane.getChildren().clear();
        dashboardStackPane.getChildren().add(load);
    }
}
