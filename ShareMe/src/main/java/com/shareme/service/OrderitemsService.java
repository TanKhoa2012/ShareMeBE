package com.shareme.service;

import com.shareme.dto.request.OrderitemsRequest;
import com.shareme.dto.response.OrderitemsResponse;
import com.shareme.entity.Orderitems;
import com.shareme.exception.ErrorCode;
import com.shareme.exception.ThrowException;
import com.shareme.mapper.OrderitemsMapper;
import com.shareme.repository.MenuitemsRepository;
import com.shareme.repository.OrderitemsRepository;
import com.shareme.repository.OrdersRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderitemsService {
    OrderitemsRepository orderitemsRepository;
    OrderitemsMapper orderitemsMapper;
    private final MenuitemsRepository menuitemsRepository;
    private final OrdersRepository ordersRepository;

    public List<OrderitemsResponse> getAllOrderitems() {
        return orderitemsRepository.findAll().stream().map(orderitemsMapper::toOrderitemsResposne).toList();
    }

    public List<OrderitemsResponse> getAllOrderitemsByOrderId(Integer orderId) {
        return orderitemsRepository.findByOrderId(orderId).stream().map(orderitemsMapper::toOrderitemsResposne).toList();
    }

    public List<OrderitemsResponse> getAllOrderitemsByMenuItemId(Integer menuItemId) {
        return orderitemsRepository.findByMenuitemId(menuItemId).stream().map(orderitemsMapper::toOrderitemsResposne).toList();
    }

    public OrderitemsResponse createOrderitems(OrderitemsRequest request) {
        Orderitems orderitems = new Orderitems().builder()
                .startDate(request.getStartDate())
                .endDate(request.getEnDate())
                .menuitems(menuitemsRepository.findById(request.getMenuItemId())
                        .orElseThrow(() -> new ThrowException(ErrorCode.MENUITEMS_NOTEXITED)))
                .orders(ordersRepository.findById(request.getOrderId())
                        .orElseThrow(() -> new ThrowException(ErrorCode.ORDERS_NOTEXITED)))
                .quantity(request.getQuantity())
                .build();

        return orderitemsMapper.toOrderitemsResposne(orderitemsRepository.save(orderitems));
    }

    public void deleteOrderitems(Integer orderId) {
        orderitemsRepository.deleteById(orderId);
    }
}
