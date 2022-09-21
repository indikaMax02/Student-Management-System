package controller;

import bo.BoFactory;
import bo.SuperBO;
import bo.custom.ManageCourseBO;
import dto.CourseDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import view.tm.CourseTM;

import java.util.List;
import java.util.Optional;

public class ManageCourseFormController {
    public TextField txtId;
    public TextField txtProgram;
    public TextField txtDuration;
    public TextField txtfee;
    public TextField txtSearch;
    public TableView<CourseTM> tblCourses;
    public TextField txtBatchNumber;
    public TextField txtNumberOfSheet;

    ManageCourseBO manageCourseBO =(ManageCourseBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.COURSE);

    public void initialize(){

        tblCourses.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("programId"));
        tblCourses.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("batch_number"));
        tblCourses.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("program"));
        tblCourses.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Sheets"));
        tblCourses.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("duration"));
        tblCourses.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("fee"));

        loadAllCourse();

        tblCourses.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{

            CourseTM selectedItem = tblCourses.getSelectionModel().getSelectedItem();

        });
    }

    private void loadAllCourse(){
        tblCourses.getItems().clear();
        List<CourseDTO> allCourses = manageCourseBO.getAllCourses();
        for (CourseDTO allCourse : allCourses) {
            tblCourses.getItems().add(new CourseTM(allCourse.getProgramId(),allCourse.getBatch_number(),allCourse.getProgram(),allCourse.getSheets(),allCourse.getDuration(),allCourse.getFee()));
        }
    }

    public void addCourseOnAction(ActionEvent actionEvent) {

        if(manageCourseBO.ifExitsCourse(txtId.getText())){
            new Alert(Alert.AlertType.WARNING,"Course Allready").showAndWait();
        }else {
              //use validation
            if (txtId.getText().isEmpty() | txtDuration.getText().isEmpty() | txtProgram.getText().isEmpty() | txtfee.getText().isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Try Again").showAndWait();
            } else {
                manageCourseBO.addCourse(new CourseDTO(txtId.getText(),txtBatchNumber.getText(), txtProgram.getText(),txtNumberOfSheet.getText(), txtDuration.getText(), Double.valueOf(txtfee.getText())));
                new Alert(Alert.AlertType.CONFIRMATION, "Course aded Complete").showAndWait();

                loadAllCourse();
            }
        }
    }

    public void updateCourseOnAction(ActionEvent actionEvent) {

        manageCourseBO.updateCourse(new CourseDTO(txtId.getText(),txtBatchNumber.getText(),txtProgram.getText(),txtNumberOfSheet.getText(),txtDuration.getText(),Double.valueOf(txtfee.getText())));
        new Alert(Alert.AlertType.CONFIRMATION,"Update Success").showAndWait();
        loadAllCourse();
    }


    public void SelectCourseOnAction(ActionEvent actionEvent) {
        CourseTM selectedItem = tblCourses.getSelectionModel().getSelectedItem();

        ButtonType ok = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"are you sure select", ok, cancel);

        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.orElse(ok)==ok){
            txtId.setText(selectedItem.getProgramId());
            txtProgram.setText(selectedItem.getProgram());
            txtDuration.setText(selectedItem.getDuration());
            txtfee.setText(String.valueOf(selectedItem.getFee()));
        }

    }
}
