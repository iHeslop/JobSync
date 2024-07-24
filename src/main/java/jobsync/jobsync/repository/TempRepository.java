package jobsync.jobsync.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jobsync.jobsync.model.Temp;
import java.util.Date;
import java.util.List;

public interface TempRepository extends JpaRepository<Temp, Long> {
    @Query("SELECT t FROM Temp t LEFT JOIN t.jobs j " +
            "WHERE j IS NULL OR (j.startDate > :endDate OR j.endDate < :startDate)")
    List<Temp> findAvailableTemps(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
