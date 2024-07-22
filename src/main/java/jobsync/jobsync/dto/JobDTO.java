package jobsync.jobsync.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jobsync.jobsync.model.Temp;

public class JobDTO {

    @NotBlank
    private String name;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    private Temp temp;

    // Getters

    public String getName() {
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

    public void setName(String name) {
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
