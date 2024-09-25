package com.shareme.mapper;
import com.shareme.dto.response.NotificationsResponse;
import com.shareme.entity.Notifications;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationsMapper {
    NotificationsResponse toNotificationsRespone(Notifications notifications);
}
