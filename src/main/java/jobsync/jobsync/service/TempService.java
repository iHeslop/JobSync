package jobsync.jobsync.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jobsync.jobsync.dto.CreateTempDTO;
import jobsync.jobsync.exceptions.ServiceValidationException;
import jobsync.jobsync.model.Temp;
import jobsync.jobsync.repository.TempRepository;

@Service
@Transactional
public class TempService {
    @Autowired
    private TempRepository tempRepository;

    public Temp createTemp(CreateTempDTO temp) throws ServiceValidationException {
        Temp newTemp = new Temp();
        newTemp.setFirstName(temp.getFirstName().trim());
        newTemp.setLastName(temp.getLastName().trim());
        return this.tempRepository.save(newTemp);
    }

    public List<Temp> getAllTemps() {
        return this.tempRepository.findAll();
    }

    public Optional<Temp> getTempById(Long id) {
        return this.tempRepository.findById(id);
    }
}
