package dto;

import java.util.Date;

public class CustomDTO {
    private String id;
    private String batchNumber;
    private String programName;
    private String registerDate;

    public CustomDTO() {
    }

    public CustomDTO(String id, String batchNumber, String programName, String registerDate) {
        this.id = id;
        this.batchNumber = batchNumber;
        this.programName = programName;
        this.registerDate = registerDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }
}
