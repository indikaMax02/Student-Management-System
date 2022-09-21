package controller;

import bo.BoFactory;
import bo.SuperBO;
import bo.custom.ManageCourseBO;
import bo.custom.ManageStudentBO;
import dto.CourseDTO;

import dto.CustomDTO;
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.CourseDetailsTM;
import view.tm.CourseModelTwoTM;
import view.tm.CourseTM;
import view.tm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class ManageStudentFormController {
    public TableView<StudentTM> tblStudent;
    public ComboBox cmbCourses;
    public RadioButton idRadio;
    public RadioButton nameRadio;
    public AnchorPane manageStudentPane;
    public TableView<CourseDetailsTM> tblCourse;


    private ManageStudentBO manageStudentBO =(ManageStudentBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.MANAGE_STUDENT);
    private List<CourseDTO> allCourses = manageStudentBO.getAllCourses();
    public void initialize(){


        ObservableList<String> course=FXCollections.observableArrayList();
        course.add("ALL");
        for (CourseDTO allCourse : allCourses) {
            course.add(allCourse.getProgram());
        }


        cmbCourses.setItems(course);
        cmbCourses.setValue("ALL");

        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("parentName"));
        tblStudent.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("mobile"));
        tblStudent.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("ol_result"));
        tblStudent.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("al_result"));
        tblStudent.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("email"));


        tblCourse.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("batchNumber"));
        tblCourse.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tblCourse.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("registerDate"));


    tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {


       if (tblStudent.getItems().isEmpty()){

       }else {


           StudentTM studentTm = newValue;
           List<CustomDTO> courseForStudent = manageStudentBO.getCourseForStudent(studentTm.getId());
           tblCourse.getItems().clear();
           for (CustomDTO customDTO : courseForStudent) {
               tblCourse.getItems().add(new CourseDetailsTM(customDTO.getBatchNumber(), customDTO.getProgramName(), customDTO.getRegisterDate()));
           }
       }




    });



    }

    private void loadStudentToTable(List<StudentDTO> studentList){
        tblStudent.getItems().clear();

        if (!(studentList==null)) {

            for (StudentDTO studentDTO : studentList) {
                tblStudent.getItems().add(new StudentTM(studentDTO.getId(), studentDTO.getNic(), studentDTO.getName(), studentDTO.getDateOfBirth(), studentDTO.getGender(), studentDTO.getParentName(), studentDTO.getMobile(), studentDTO.getAddress(), studentDTO.getOl_result(), studentDTO.getAl_result(), studentDTO.getEmail()));
            }
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
        if (cmbCourses.getSelectionModel().getSelectedItem()=="ALL"){
              loadStudentToTable(manageStudentBO.getAllStudent());
        }else{
            for (CourseDTO course : allCourses) {
                if (course.getProgram()==cmbCourses.getSelectionModel().getSelectedItem()){
                    List<StudentDTO> studentDTOList = manageStudentBO.getStudentFromCourse(course.getProgramId());
                    loadStudentToTable(studentDTOList);
                }
            }
        }
    }

    public void updateOnAction(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/UpdateStudentForm.fxml"));
        Parent parent = loader.load();
        UpdateStudentFormController controller = loader.<UpdateStudentFormController>getController();
        StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
        controller.txtStudentId.setText(selectedItem.getId());
        controller.txtName.setText(selectedItem.getName());
        controller.txtNIC.setText(selectedItem.getNic());
        controller.txtdateOfBirth.setText(selectedItem.getDateOfBirth());
        controller.txtGender.setText(selectedItem.getGender());
        controller.txtParent.setText(selectedItem.getParentName());
        controller.txtContact.setText(selectedItem.getMobile());
        controller.txtAddress.setText(selectedItem.getAddress());
        controller.txtOlResult.setText(selectedItem.getOl_result());
        controller.txtALResult.setText(selectedItem.getAl_result());
        controller.txtEmail.setText(selectedItem.getEmail());

        List<CustomDTO> courseForStudent = manageStudentBO.getCourseForStudent(selectedItem.getId());
        controller.loadCourseTable(courseForStudent);

        manageStudentPane.getChildren().clear();
        manageStudentPane.getChildren().add(parent);
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();

        boolean b = manageStudentBO.deleteStudent(new StudentDTO(selectedItem.getId(), selectedItem.getNic(), selectedItem.getName(), selectedItem.getDateOfBirth(), selectedItem.getGender(), selectedItem.getParentName(), selectedItem.getMobile(), selectedItem.getAddress(), selectedItem.getOl_result(), selectedItem.getAl_result(), selectedItem.getEmail()));

        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"deleted student").showAndWait();
            tblStudent.getItems().remove(selectedItem);
        }else {
            new Alert(Alert.AlertType.WARNING,"Operation Fail ! .You Must Delete Student Courses").showAndWait();
        }

        }


    public void idRadioButtonOnMouseClicked(MouseEvent mouseEvent) {
        if (idRadio.isSelected()) {
            nameRadio.setSelected(false);
        }
    }

    public void nameRadioButtonOnMouseClicked(MouseEvent mouseEvent) {
        if (nameRadio.isSelected()){
            idRadio.setSelected(false);
        }
    }

    public void cmbCourseMouseCliked(MouseEvent mouseEvent) {

    }

    public void cmbCourseTouch(TouchEvent touchEvent) {
        System.out.println("hello");
    }
}
