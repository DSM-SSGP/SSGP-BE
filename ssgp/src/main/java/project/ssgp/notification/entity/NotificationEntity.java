package project.ssgp.notification.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;
import project.ssgp.product.entity.Selling;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Document("notification")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEntity {

    @Id
    private int id;

    private String title;

    private String message;

    private List<Selling> sellings;

    @CreationTimestamp
    private LocalDate date;

}