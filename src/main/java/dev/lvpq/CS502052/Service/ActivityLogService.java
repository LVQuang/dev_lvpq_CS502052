package dev.lvpq.CS502052.Service;

import dev.lvpq.CS502052.Entity.ActivityLog;
import dev.lvpq.CS502052.Entity.User;
import dev.lvpq.CS502052.Enums.Activity;
import dev.lvpq.CS502052.Repository.ActivityLogRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class ActivityLogService {
    ActivityLogRepository activityLogRepository;

    public ActivityLog create(Activity activity, String keyword, User user) {
        var activityLog = ActivityLog.builder()
                .activity(activity)
                .keyword(keyword)
                .build();

        if (user != null)  activityLog.setUser(user);

        return activityLogRepository.save(activityLog);
    }
}
