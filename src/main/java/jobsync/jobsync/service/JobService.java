package jobsync.jobsync.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jobsync.jobsync.dto.JobDTO;
import jobsync.jobsync.exceptions.ServiceValidationException;
import jobsync.jobsync.model.Job;
import jobsync.jobsync.repository.JobRepository;

@Service
@Transactional
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public Job createJob(JobDTO job) throws ServiceValidationException {
        Job newJob = new Job();
        newJob.setJobName(job.getJobName().trim());
        newJob.setStartDate(job.getStartDate());
        newJob.setEndDate(job.getEndDate());
        return this.jobRepository.save(newJob);
    }

    public List<Job> getAllJobs() {
        return this.jobRepository.findAll();
    }

    public Optional<Job> updateById(Long id, JobDTO job) {
        Optional<Job> maybeJob = this.jobRepository.findById(id);
        if (maybeJob.isEmpty()) {
            return maybeJob;
        }
        Job foundJob = maybeJob.get();
        foundJob.setJobName(job.getJobName().trim());
        foundJob.setStartDate(job.getStartDate());
        foundJob.setEndDate(job.getEndDate());
        foundJob.setTemp(job.getTemp());
        Job updatedJob = this.jobRepository.save(foundJob);
        return Optional.of(updatedJob);
    }

    public Optional<Job> getJobById(Long id) {
        return this.jobRepository.findById(id);
    }
}
