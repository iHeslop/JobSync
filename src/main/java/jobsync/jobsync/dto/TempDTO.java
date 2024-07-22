package jobsync.jobsync.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class TempDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private List<JobDTO> jobs;

    // Getters

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<JobDTO> getJobs() {
        return jobs;
    }

    // Setters

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }

    public void setJobs(List<JobDTO> jobs) {
        this.jobs = jobs;
    }

}
