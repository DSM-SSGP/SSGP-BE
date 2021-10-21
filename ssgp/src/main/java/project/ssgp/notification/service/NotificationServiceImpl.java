package project.ssgp.notification.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import project.ssgp.exception.UserNotFoundException;
import project.ssgp.notification.entity.NotificationEntity;
import project.ssgp.notification.payload.request.FcmRequest;
import project.ssgp.notification.payload.response.NotificationResponse;
import project.ssgp.notification.repository.NotificationRepository;
import project.ssgp.security.AuthenticationFacade;
import project.ssgp.user.entity.UserEntity;
import project.ssgp.user.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);
    private static final String FIREBASE_CONFIG_PATH = "ssgp-firebase-adminsdk.json";

    private final AuthenticationFacade authenticationFacade;

    @Override
    @PostConstruct
    public void noticeInitialize() {
        try {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(FIREBASE_CONFIG_PATH).getInputStream())).build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                logger.info("Firebase application has been initialized");
            }
        } catch (IOException ioException) {
            logger.error(ioException.getMessage());
        }
    }

    @Override
    public void sendNotice(FcmRequest fcmRequest) {
        try {
            if (fcmRequest.getToken() != null) {
                var message = Message.builder()
                        .setToken(fcmRequest.getToken())
                        .putData("product_id", fcmRequest.getProductId().toString())
                        .setNotification(Notification.builder()
                                        .setTitle(fcmRequest.getTitle())
                                        .setBody(fcmRequest.getMessage())
                                        .build()
                        ).setApnsConfig(ApnsConfig.builder()
                                .setAps(Aps.builder()
                                        .setSound("default")
                                        .build()
                                ).build()
                        )
                        .build();


                String response = FirebaseMessaging.getInstance().sendAsync(message).get();
                logger.info("Sent Message" + response);
            }

        } catch (ExecutionException e) {
            logger.error(e.getMessage());
        } catch (InterruptedException e){
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }

    }


    @Override
    public void noticeSwitch(FcmRequest fcmRequest) {

        UserEntity user = userRepository.findById(fcmRequest.getUserId())
                .orElseThrow(UserNotFoundException::new);

        boolean isNoticed = user.isNotice();

        if (isNoticed == true) {
            isNoticed = false;
        } else {
            isNoticed = true;
        }

    }

    @Override
    public List<NotificationResponse> getNoticeList() {

        UserEntity user = authenticationFacade.getUser();

        List<NotificationEntity> notificationList = notificationRepository.findByUser(user.getId());

        List<NotificationResponse> notificationResponses = new ArrayList<>();

        for(NotificationEntity notification : notificationList) {
            List<String> brand = notification.getSellings().stream()
                    .map(selling -> selling.getBrand().getName())
                    .collect(Collectors.toList());
            notificationResponses.add(
                    NotificationResponse.builder()
                            .notificationId(notification.getId())
                            .title(notification.getTitle())
                            .message(notification.getMessage())
                            .date(notification.getDate())
                            .notice(user.isNotice())
                            .brand(brand)
                            .build()

            );
        }

        return notificationResponses;
    }
}
