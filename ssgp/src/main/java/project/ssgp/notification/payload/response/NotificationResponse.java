package project.ssgp.notification.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.ssgp.product.payload.response.SellingResponse;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {

    private int notificationId;

    private String title;

    private String message;

    private List<String> brand;

    private LocalDate date;

    private boolean notice;

}
