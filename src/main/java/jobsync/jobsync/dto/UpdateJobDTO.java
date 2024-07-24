package jobsync.jobsync.dto;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UpdateJobDTO {
    @Pattern(regexp = ".*\\S.*", message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Start date cannot be null")
    private Date startDate;

    @NotNull(message = "End date cannot be null")
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
