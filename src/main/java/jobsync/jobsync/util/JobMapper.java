package jobsync.jobsync.util;

import org.springframework.stereotype.Component;

import jobsync.jobsync.dto.JobDTO;
import jobsync.jobsync.dto.TempDTO;
import jobsync.jobsync.model.Job;
import jobsync.jobsync.model.Temp;

@Component
public class JobMapper {
    public JobDTO toDTO(Job job) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setJobName(job.getJobName());
        jobDTO.setStartDate(job.getStartDate());
        jobDTO.setEndDate(job.getEndDate());
        if (job.getTemp() != null) {
            TempDTO tempDTO = new TempDTO();
            tempDTO.setFirstName(job.getTemp().getFirstName());
            tempDTO.setLastName(job.getTemp().getLastName());
            jobDTO.setTemp(job.getTemp());
        }
        return jobDTO;
    }

    public Job toEntity(JobDTO jobDTO) {
        Job job = new Job();
        job.setJobName(jobDTO.getJobName());
        job.setStartDate(jobDTO.getStartDate());
        job.setEndDate(jobDTO.getEndDate());
        if (jobDTO.getTemp() != null) {
            Temp temp = new Temp();
            temp.setFirstName(jobDTO.getTemp().getFirstName());
            temp.setLastName(jobDTO.getTemp().getLastName());
            job.setTemp(temp);
        }
        return job;
    }
}
