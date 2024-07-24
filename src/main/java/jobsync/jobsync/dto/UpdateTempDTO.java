package jobsync.jobsync.dto;

import java.util.List;

import jakarta.validation.constraints.Pattern;

public class UpdateTempDTO {

    @Pattern(regexp = ".*\\S.*", message = "First name cannot be empty")
    private String firstName;

    @Pattern(regexp = ".*\\S.*", message = "First name cannot be empty")
    private String lastName;

    private List<Long> jobIds;

    // Getters

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Long> getJobIds() {
        return jobIds;
    }
}
