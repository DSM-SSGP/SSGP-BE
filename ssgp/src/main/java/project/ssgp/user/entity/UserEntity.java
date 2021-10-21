package project.ssgp.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import project.ssgp.notification.entity.NotificationEntity;

import javax.persistence.Id;
import java.util.List;

@Document("user")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    private String id;

    private String password;

    private boolean notice;

    private List<NotificationEntity> notifications;

    public UserEntity updatePassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isNotice() {
        return this.notice = true;
    }

}