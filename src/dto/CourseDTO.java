package dto;

public class CourseDTO{
    private String programId;
    private String batch_number;
    private String program;
    private String Sheets;
    private String duration;
    private double fee;

    public CourseDTO() {
    }

    public CourseDTO(String programId, String batch_number, String program, String sheets, String duration, double fee) {
        this.programId = programId;
        this.batch_number = batch_number;
        this.program = program;
        Sheets = sheets;
        this.duration = duration;
        this.fee = fee;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getBatch_number() {
        return batch_number;
    }

    public void setBatch_number(String batch_number) {
        this.batch_number = batch_number;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getSheets() {
        return Sheets;
    }

    public void setSheets(String sheets) {
        Sheets = sheets;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }



}
