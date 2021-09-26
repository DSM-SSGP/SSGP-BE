package project.ssgp.entity.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;
import project.ssgp.entity.user.User;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Document("tbl_notice")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Notice {

    @Id
    private int id;

    @ManyToOne
    @Column(name = "user_id")
    private User user;

    private String content;

    private BrandEnum brand;

    @CreationTimestamp
    @Column(name = "create_at")
    private LocalDate date;

}
