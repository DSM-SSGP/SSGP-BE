package project.ssgp.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("user")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;

    private String password;

    private boolean notice;

    public User updatePassword(String password) {
        this.password = password;
        return this;
    }

}