package jobsync.jobsync.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jobsync.jobsync.model.Temp;

public class JobDTO {

    @NotBlank
    private String name;

    @NotBlank
    private Date startDate;

    @NotBlank
    private Date endDate;

    private Temp temp;
    // Getters

    public String getJobName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Temp getTemp() {
        return temp;
    }

    // Setters

    public void setJobName(String name) {
        this.name = name;
    }

    public void setStartDate(Date date) {
        this.startDate = date;
    }

    public void setEndDate(Date date) {
        this.endDate = date;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

}
