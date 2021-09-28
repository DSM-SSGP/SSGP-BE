package project.ssgp.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("tbl_user")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;

    private String password;

}
