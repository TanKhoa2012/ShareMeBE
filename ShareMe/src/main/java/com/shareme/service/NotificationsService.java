package com.shareme.service;

import com.shareme.dto.request.NotificationsRequest;
import com.shareme.dto.response.NotificationsResponse;
import com.shareme.entity.Notifications;
import com.shareme.exception.ErrorCode;
import com.shareme.exception.ThrowException;
import com.shareme.mapper.NotificationsMapper;
import com.shareme.repository.NotificationsRepository;
import com.shareme.repository.StoresRepository;
import com.shareme.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationsService {
    NotificationsRepository notificationsRepository;
    NotificationsMapper notificationsMapper;
    private final UserRepository userRepository;
    private final StoresRepository storesRepository;

    public List<NotificationsResponse> getNotifications() {
        return notificationsRepository.findAll().stream()
                .map(notificationsMapper::toNotificationsRespone).toList();
    }

    public List<NotificationsResponse> getNotificationsByUserId(Integer userId) {
        return notificationsRepository.findByUserId(userId).stream()
                .map(notificationsMapper::toNotificationsRespone).toList();
    }

    public List<NotificationsResponse> getNotificationsByStoreId(Integer storeId) {
        return notificationsRepository.findByStoreId(storeId).stream()
                .map(notificationsMapper::toNotificationsRespone).toList();
    }

    public NotificationsResponse createNotification(NotificationsRequest request) {
        Notifications notifications = new Notifications().builder()
                .users(userRepository.findById(request.getUserId())
                        .orElseThrow(() -> new ThrowException(ErrorCode.USER_NOTEXITED)))
                .stores(storesRepository.findById(request.getStoreId())
                        .orElseThrow(() -> new ThrowException(ErrorCode.STORE_NOTEXITED)))
                .messages(request.getMessage())
                .sentDate(new Date(Instant.now().toEpochMilli()))
                .build();

        return notificationsMapper.toNotificationsRespone(notificationsRepository.save(notifications));
    }

    public void deleteNotification(Integer notificationId) {
        notificationsRepository.deleteById(notificationId);
    }


}
