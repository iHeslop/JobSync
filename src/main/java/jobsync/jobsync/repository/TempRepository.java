package jobsync.jobsync.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jobsync.jobsync.model.Temp;

public interface TempRepository extends JpaRepository<Temp, Long> {

}
