package project.ssgp.notification.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import project.ssgp.notification.entity.NotificationEntity;
import project.ssgp.user.entity.UserEntity;

import java.util.List;

public interface NotificationRepository extends MongoRepository<NotificationEntity, Integer> {

    boolean existsByNoticeUserIds(UserEntity user);

    List<NotificationEntity> findByUser(String userId);

}
