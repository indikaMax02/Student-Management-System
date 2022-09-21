package view.tm;

import javafx.scene.control.Button;

import java.awt.*;

public class CourseModelTwoTM {
    private String programName;
    private Button remove;

    public CourseModelTwoTM() {
    }

    public CourseModelTwoTM(String programName, Button remove) {
        this.programName = programName;
        this.remove = remove;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public Button getRemove() {
        return remove;
    }

    public void setRemove(Button remove) {
        this.remove = remove;
    }
}
