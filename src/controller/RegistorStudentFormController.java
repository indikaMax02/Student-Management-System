package controller;

import bo.BoFactory;
import bo.SuperBO;
import bo.custom.RegisterStudentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import dto.CourseDTO;
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import util.ValidationUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class RegistorStudentFormController {
    public TextField txtStudentId;
    public TextField txtName;
    public TextField txtNIC;
    public TextField txtdateOfBirth;
    public TextField txtParent;
    public TextField txtContact;
    public TextField txtAddress;
    public TextField txtOlResult;
    public TextField txtALResult;
    public TextField txtEmail;
    public JFXButton btnRegister;
    public JFXComboBox cmdCourse;
    public JFXComboBox cmbGender;
    public TextArea txtMetadata;
    public TextField txtBatchNumber;
    Pattern idPattern = Pattern.compile("^[0-9]{1,4}$");
    Pattern namePattern = Pattern.compile("^[A-z]*[ ]?[A-z]*[ ]?[A-z]$");
    Pattern nicPattern = Pattern.compile("^[0-9]{9}[V,v]|[0-9]{12}$");
    Pattern dateOfBirthPattern = Pattern.compile("^[0-9]{4}[-|/][0-9]{2}[-|/][0-9]{2}$");
    Pattern parentNamePattern = Pattern.compile("^[A-z.]*[A-z]{3,}[ ]?[A-z]*[ ]?[A-z]*$");
    Pattern phoneNumberPattern = Pattern.compile("^[0]{1}[7][0|1|2|4|5|6|7|8|0][0-9]{7}$");
    Pattern addressPattern = Pattern.compile("^[A-z,.0-9/]+$");
    Pattern olresult=Pattern.compile("^[A-z,.0-9/-]+$");
    Pattern email=Pattern.compile("^[a-z0-9]+(@gmail.com){1}$");


    LinkedHashMap<TextField,Pattern> hashMap=new LinkedHashMap<>();
    RegisterStudentBO registerStudentBO =(RegisterStudentBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.REGISTER_STUDENT);

    List<CourseDTO> allCourses = registerStudentBO.getAllCourses();
    public void initialize() {





        ObservableList<String> gender = FXCollections.observableArrayList();
        gender.add("male");
        gender.add("female");
        gender.add("other");
        cmbGender.setItems(gender);

        List<String> courseName=new ArrayList<>();

        for (CourseDTO c : allCourses) {
            if (!courseName.contains(c.getProgram())){
                courseName.add(c.getProgram());
            }
        }
        cmdCourse.setItems(FXCollections.observableArrayList(courseName));



         cmdCourse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
             for (CourseDTO c : allCourses) {
                 if (newValue.equals(c.getProgram())){
                     txtBatchNumber.setText(c.getBatch_number());
                 }
             }




             if (cmdCourse.getValue()!="course" &txtStudentId.getParent().getStyle().contains("-fx-border-color: green")
            & txtName.getParent().getStyle().contains("-fx-border-color: green")
            & txtNIC.getParent().getStyle().contains("-fx-border-color: green")
            & txtdateOfBirth.getParent().getStyle().contains("-fx-border-color: green")
            & cmbGender.getParent().getStyle().contains("-fx-border-color: green")
            &  txtParent.getParent().getStyle().contains("-fx-border-color: green")
            & txtContact.getParent().getStyle().contains("-fx-border-color: green")
            & txtAddress.getParent().getStyle().contains("-fx-border-color: green")
            & txtOlResult.getParent().getStyle().contains("-fx-border-color: green")
            & txtALResult.getParent().getStyle().contains("-fx-border-color: green")
            & txtEmail.getParent().getStyle().contains("-fx-border-color: green")

             ){
                btnRegister.setDisable(false);

             }

         });



            hashmapDataSet();


    }


    private void hashmapDataSet() {
        hashMap.put(txtStudentId,idPattern);
        hashMap.put(txtName,namePattern);
        hashMap.put(txtNIC,nicPattern);
        hashMap.put(txtdateOfBirth,dateOfBirthPattern);
        hashMap.put(txtParent,parentNamePattern);
        hashMap.put(txtContact,phoneNumberPattern);
        hashMap.put(txtAddress,addressPattern);
        hashMap.put(txtOlResult,olresult);
        hashMap.put(txtALResult,olresult);
        hashMap.put(txtEmail,email);

    }


    public void registerStudentOnAction(ActionEvent actionEvent) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(txtStudentId.getText());
        studentDTO.setNic(txtNIC.getText());
        studentDTO.setName(txtName.getText());
        studentDTO.setDateOfBirth(txtdateOfBirth.getText());
        studentDTO.setGender(String.valueOf(cmbGender.getValue()));
        studentDTO.setParentName(txtParent.getText());
        studentDTO.setMobile(txtContact.getText());
        studentDTO.setAddress(txtAddress.getText());
        studentDTO.setOl_result(txtOlResult.getText());
        studentDTO.setAl_result(txtALResult.getText());
        studentDTO.setEmail(txtEmail.getText());

        for (CourseDTO course : allCourses) {
            if (cmdCourse.getValue().equals(course.getProgram())){
                studentDTO.setCourseDTO(course);
                break;
            }
        }





        if (txtStudentId.getText().isEmpty()){
                  new Alert(Alert.AlertType.WARNING,"Fail").showAndWait();
               }else {
            registerStudentBO.registerStudent(studentDTO);
            new Alert(Alert.AlertType.CONFIRMATION,"Student Regiter Complete").showAndWait();
        }
    }




    public void textField_KeyReleased(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(hashMap,btnRegister);
        if (keyEvent.getCode()== KeyCode.ENTER) {
            if(response instanceof TextField){
                TextField textField= (TextField) response;

                    textField.requestFocus();

            }
        }


    }




    public void dateOfBirth_KeyPress(KeyEvent keyEvent) {
       if(txtdateOfBirth.isFocused() & txtdateOfBirth.getParent().getStyle().contains("-fx-border-color: green")){
             if (keyEvent.getCode()==KeyCode.ENTER){
                 cmbGender.requestFocus();
             }
       }
    }

    public void gender_OnKeyPress(KeyEvent keyEvent) {
        if (cmbGender.getValue()=="male" | cmbGender.getValue()=="female" | cmbGender.getValue()=="other"){
            cmbGender.getParent().setStyle("-fx-border-color: green");
            if (keyEvent.getCode()==KeyCode.ENTER){
                txtParent.requestFocus();
            }
        }else {
            cmbGender.getParent().setStyle("-fx-border-color: red");
            cmbGender.requestFocus();
        }
    }

    public void email_OnKeyPressed(KeyEvent keyEvent) {
        btnRegister.setDisable(true);
        if (txtEmail.isFocused() & txtEmail.getParent().getStyle().contains("-fx-border-color: green")){
            if (keyEvent.getCode()==KeyCode.ENTER){
                cmdCourse.requestFocus();
            }
        }
    }
}
