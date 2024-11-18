package dev.lvpq.CS502052.Repository;

import dev.lvpq.CS502052.Entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, String> {

}
