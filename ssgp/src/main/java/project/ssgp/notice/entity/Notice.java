package project.ssgp.notice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;
import project.ssgp.product.entity.BrandEnum;
import project.ssgp.user.entity.User;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Document("notice")
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
