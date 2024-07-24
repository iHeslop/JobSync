package jobsync.jobsync.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jobsync.jobsync.dto.CreateJobDTO;
import jobsync.jobsync.dto.UpdateJobDTO;
import jobsync.jobsync.exceptions.NotFoundException;
import jobsync.jobsync.exceptions.ServiceValidationException;
import jobsync.jobsync.model.Job;
import jobsync.jobsync.service.JobService;

import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping()
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> allJobs = this.jobService.getAllJobs();
        return new ResponseEntity<>(allJobs, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Job> createJob(@Valid @RequestBody CreateJobDTO data) throws BadRequestException {
        Job createdJob;
        try {
            createdJob = this.jobService.createJob(data);
            return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
        } catch (ServiceValidationException e) {
            e.printStackTrace();
            throw new BadRequestException(e.generateMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Job> updateJobById(@PathVariable Long id, @Valid @RequestBody UpdateJobDTO data) {
        Optional<Job> maybeJob = this.jobService.updateById(id, data);
        Job updatedJob = maybeJob.orElseThrow(() -> new NotFoundException(Job.class, id));
        return new ResponseEntity<>(updatedJob, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) throws NotFoundException {
        Optional<Job> maybeJob = this.jobService.getJobById(id);
        Job foundJob = maybeJob.orElseThrow(() -> new NotFoundException(Job.class, id));
        return new ResponseEntity<>(foundJob, HttpStatus.OK);
    }

    @GetMapping(params = "assigned")
    public ResponseEntity<List<Job>> getJobsByAssigned(@RequestParam Boolean assigned) {
        List<Job> assignedJobs = this.jobService.getJobsByAssigned(assigned);
        return new ResponseEntity<>(assignedJobs, HttpStatus.OK);
    }

}
