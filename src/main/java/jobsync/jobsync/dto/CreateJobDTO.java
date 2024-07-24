package jobsync.jobsync.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateJobDTO {

    @NotBlank
    private String name;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    private Long tempId;

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

    public Long getTempId() {
        return tempId;
    }
}
