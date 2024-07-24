package jobsync.jobsync.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import jobsync.jobsync.model.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByTempIsNotNull();

    List<Job> findByTempIsNull();
}
