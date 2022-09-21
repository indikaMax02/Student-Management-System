package controller;

import bo.BoFactory;
import bo.SuperBO;
import bo.custom.ManageCourseBO;
import bo.custom.ManageStudentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dao.custom.CourseDAO;
import dto.CourseDTO;
import dto.CustomDTO;
import dto.StudentCoursesDTO;
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sun.nio.cs.ArrayEncoder;
import view.tm.CourseModelTwoTM;

import java.util.List;

public class UpdateStudentFormController {
    public TextField txtStudentId;
    public TextField txtName;
    public TextField txtNIC;
    public TextField txtdateOfBirth;
    public TextField txtParent;
    public TextField txtContact;
    public TextArea txtAddress;
    public TextField txtOlResult;
    public TextField txtALResult;
    public TextField txtEmail;
    public JFXButton btnRegister;
    public TableView<CourseModelTwoTM> tblCourse;
    public JFXComboBox cmdCourse;
    public TextField txtGender;
    public AnchorPane updateStudentPane;







    private ManageStudentBO manageStudentBO =(ManageStudentBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.MANAGE_STUDENT);
    private List<CourseDTO> allCourses = manageStudentBO.getAllCourses();
    public void initialize(){



        ObservableList<String> courseNames= FXCollections.observableArrayList();
        for (CourseDTO allCourses : allCourses) {
            courseNames.add(allCourses.getProgram());
        }
        cmdCourse.setItems(courseNames);

        tblCourse.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("programName"));
        tblCourse.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("remove"));



        tblCourse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            courseTblAction();
        });
        cmdCourse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

        });


    }

    public void loadCourseTable(List<CustomDTO> courseForStudent){

        if (courseForStudent==null){
            tblCourse.getItems().clear();
        }else {
            for (CustomDTO customDTO : courseForStudent) {
                Button remove = new Button("Remove");
                tblCourse.getItems().add(new CourseModelTwoTM(customDTO.getProgramName(), remove));
            }
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        StudentDTO studentDTO = new StudentDTO(txtStudentId.getText(),txtNIC.getText(),txtName.getText(),txtdateOfBirth.getText(),txtGender.getText(),txtParent.getText(),txtContact.getText(),txtAddress.getText(),txtOlResult.getText(),txtALResult.getText(),txtEmail.getText());

        manageStudentBO.updateStudent(studentDTO);
        new Alert(Alert.AlertType.CONFIRMATION,"Student Updated").showAndWait();

    }

    public void addCourseOnAction(ActionEvent actionEvent) {

        for (CourseDTO course : allCourses) {
            if (cmdCourse.getValue().equals(course.getProgram())){

                List<CourseModelTwoTM> items = tblCourse.getItems();

                if (tblCourse.getItems().isEmpty()){
                    tblCourse.getItems().add(new CourseModelTwoTM(course.getProgram(),new Button("remove")));

                    StudentDTO studentDTO = new StudentDTO(txtStudentId.getText(),txtNIC.getText(),txtName.getText(),txtdateOfBirth.getText(),txtGender.getText(),txtParent.getText(),txtContact.getText(),txtAddress.getText(),txtOlResult.getText(),txtALResult.getText(),txtEmail.getText());

                    CourseDTO courseDTO = new CourseDTO(course.getProgramId(),course.getBatch_number(),course.getProgram(),course.getSheets(),course.getDuration(),course.getFee());


                    StudentCoursesDTO studentCoursesDTO = new StudentCoursesDTO(studentDTO,courseDTO);
                    manageStudentBO.addCourseToStudent(studentCoursesDTO);


                }else {
                    for (CourseModelTwoTM item : items) {
                        if (item.getProgramName() == course.getProgram()) {
                            new Alert(Alert.AlertType.WARNING, "Data Duplicate").showAndWait();
                        } else {
                            tblCourse.getItems().add(new CourseModelTwoTM(course.getProgram(),new Button("remove")));

                            StudentDTO studentDTO = new StudentDTO(txtStudentId.getText(),txtNIC.getText(),txtName.getText(),txtdateOfBirth.getText(),txtGender.getText(),txtParent.getText(),txtContact.getText(),txtAddress.getText(),txtOlResult.getText(),txtALResult.getText(),txtEmail.getText());

                            CourseDTO courseDTO = new CourseDTO(course.getProgramId(),course.getBatch_number(),course.getProgram(),course.getSheets(),course.getDuration(),course.getFee());


                            StudentCoursesDTO studentCoursesDTO = new StudentCoursesDTO(studentDTO,courseDTO);
                            manageStudentBO.addCourseToStudent(studentCoursesDTO);
                        }
                    }
                }
            }
        }

    }

    private void deleteStudentCourse() {

    }
    public void tblCourseOnMouseClicked(MouseEvent mouseEvent) {





    }

    private void courseTblAction(){
        if (!tblCourse.getItems().isEmpty()){
            Button remove = tblCourse.getSelectionModel().getSelectedItem().getRemove();
            remove.setOnAction(event -> {
                for (CourseDTO course : allCourses) {
                    if (tblCourse.getSelectionModel().getSelectedItem().getProgramName().equals(course.getProgram())) {
                        System.out.println(course.getProgramId());

                        StudentDTO studentDTO = new StudentDTO(txtStudentId.getText(),txtNIC.getText(),txtName.getText(),txtdateOfBirth.getText(),txtGender.getText(),txtParent.getText(),txtContact.getText(),txtAddress.getText(),txtOlResult.getText(),txtALResult.getText(),txtEmail.getText());

                        CourseDTO courseDTO = new CourseDTO(course.getProgramId(),course.getBatch_number(),course.getProgram(),course.getSheets(),course.getDuration(),course.getFee());

                        manageStudentBO.deleteStudentCourse(studentDTO, courseDTO);

                    }
                }
                tblCourse.getItems().remove(tblCourse.getSelectionModel().getSelectedItem());
            });
        }
    }

    public void textField_KeyReleased(KeyEvent keyEvent) {
    }
}
