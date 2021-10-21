package project.ssgp.notification.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import project.ssgp.product.entity.Selling;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

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
    @Column(name = "create_at")
    private LocalDate date;

}