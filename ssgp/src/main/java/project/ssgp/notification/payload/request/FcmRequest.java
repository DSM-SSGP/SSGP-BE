package project.ssgp.notification.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FcmRequest {

    private String userId;

    private String token;

    private String title;

    private String message;

    private Integer productId;

    public void setMessage(String message){
        this.message = message;
    }

}