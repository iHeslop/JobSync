package jobsync.jobsync.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jobsync.jobsync.dto.CreateTempDTO;
import jobsync.jobsync.exceptions.NotFoundException;
import jobsync.jobsync.exceptions.ServiceValidationException;
import jobsync.jobsync.model.Job;
import jobsync.jobsync.model.Temp;
import jobsync.jobsync.repository.TempRepository;

@Service
@Transactional
public class TempService {
    @Autowired
    private TempRepository tempRepository;

    @Autowired
    @Lazy
    private JobService jobService;

    @Autowired
    private ModelMapper modelMapper;

    public Temp createTemp(CreateTempDTO tempDTO) throws ServiceValidationException {
        Temp newTemp = modelMapper.map(tempDTO, Temp.class);
        return this.tempRepository.save(newTemp);
    }

    public List<Temp> getAllTemps() {
        return this.tempRepository.findAll();
    }

    public Optional<Temp> getTempById(Long id) {
        return this.tempRepository.findById(id);
    }

    public List<Temp> getAvailableTemps(Long jobId) {
        Optional<Job> maybeJob = jobService.getJobById(jobId);
        Job foundJob = maybeJob.orElseThrow(() -> new NotFoundException(Job.class, jobId));
        Date startDate = foundJob.getStartDate();
        Date endDate = foundJob.getEndDate();
        return tempRepository.findAvailableTemps(startDate, endDate);
    }

}
