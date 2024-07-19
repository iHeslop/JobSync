package jobsync.jobsync.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jobsync.jobsync.model.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

}
