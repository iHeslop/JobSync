package jobsync.jobsync.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jobsync.jobsync.dto.CreateJobDTO;
import jobsync.jobsync.dto.UpdateJobDTO;
import jobsync.jobsync.exceptions.ServiceValidationException;
import jobsync.jobsync.exceptions.ValidationErrors;
import jobsync.jobsync.model.Job;
import jobsync.jobsync.model.Temp;
import jobsync.jobsync.repository.JobRepository;

@Service
@Transactional
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private TempService tempService;

    @Autowired
    private ModelMapper modelMapper;

    public Job createJob(CreateJobDTO jobDTO) throws ServiceValidationException {
        Job newJob = modelMapper.map(jobDTO, Job.class);
        return this.jobRepository.save(newJob);
    }

    public List<Job> getAllJobs() {
        return this.jobRepository.findAll();
    }

    public Optional<Job> updateById(Long id, UpdateJobDTO jobDTO) throws ServiceValidationException {
        Optional<Job> maybeJob = this.jobRepository.findById(id);
        if (maybeJob.isEmpty()) {
            return maybeJob;
        }
        Job foundJob = maybeJob.get();
        modelMapper.map(jobDTO, foundJob);
        ValidationErrors errors = new ValidationErrors();
        if (jobDTO.getTempId() != null) {
            Optional<Temp> maybeTemp = tempService.getTempById(jobDTO.getTempId());
            if (maybeTemp.isPresent()) {
                foundJob.setTemp(maybeTemp.get());
            } else {
                errors.addError("temp", "Temp with ID " + jobDTO.getTempId() + " does not exist.");
            }
        } else {
            foundJob.setTemp(null);
        }

        if (errors.hasErrors()) {
            throw new ServiceValidationException(errors);
        }

        Job updatedJob = this.jobRepository.save(foundJob);
        return Optional.of(updatedJob);
    }

    public Optional<Job> getJobById(Long id) {
        return this.jobRepository.findById(id);
    }

    public List<Job> getJobsByAssigned(Boolean assigned) {
        if (assigned) {
            return jobRepository.findByTempIsNotNull();
        } else {
            return jobRepository.findByTempIsNull();
        }
    }
}
