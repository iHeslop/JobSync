package jobsync.jobsync.service;

import java.util.List;

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
}
