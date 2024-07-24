package jobsync.jobsync.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class CreateTempDTO {

    @NotBlank
    private String firstName;

    @NotBlank
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
