package project.ssgp.notification.service;

import project.ssgp.notification.payload.request.FcmRequest;
import project.ssgp.notification.payload.response.NotificationResponse;

import java.util.List;

public interface NotificationService {

    void noticeInitialize();

    void sendNotice(FcmRequest fcmRequest);

    void noticeSwitch(FcmRequest fcmRequest);

    List<NotificationResponse> getNoticeList();

}
