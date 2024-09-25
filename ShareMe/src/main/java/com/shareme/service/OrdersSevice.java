package com.shareme.service;

import com.shareme.constant.OrderStatus;
import com.shareme.dto.request.OrdersRequest;
import com.shareme.dto.request.OrdersUpdateRequest;
import com.shareme.dto.response.OrdersResponse;
import com.shareme.entity.Orders;
import com.shareme.exception.ErrorCode;
import com.shareme.exception.ThrowException;
import com.shareme.mapper.OrdersMapper;
import com.shareme.repository.OrdersRepository;
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
public class OrdersSevice {
    OrdersRepository ordersRepository;
    OrdersMapper ordersMapper;
    private final UserRepository userRepository;
    private final StoresRepository storesRepository;

    public List<OrdersResponse> getAllOrders() {
        return ordersRepository.findAll().stream().map(ordersMapper::toOrdersResponse).toList();
    }

    public OrdersResponse getOrderById(Integer orderId) {
        return ordersMapper.toOrdersResponse(ordersRepository.getById(orderId));
    }

    public List<OrdersResponse> getOrdersByStoreId(Integer storeId) {
        return ordersRepository.findByStoreId(storeId).stream()
                .map(ordersMapper::toOrdersResponse).toList();
    }

    public List<OrdersResponse> getOrdersByUserId(Integer userId) {
        return ordersRepository.findByUserId(userId).stream()
                .map(ordersMapper::toOrdersResponse).toList();
    }

    public OrdersResponse createOrder(OrdersRequest request) {
        Orders order = new Orders().builder()
                .createdDate(new Date(Instant.now().toEpochMilli()))
                .deliveryFee(request.getDeliveryFee())
                .totalPrice(request.getTotalPrice())
                .paymentMethod(request.getPaymentMethod())
                .status(OrderStatus.PENDING)
                .users(userRepository.findById(request.getUserId())
                        .orElseThrow(()-> new ThrowException(ErrorCode.USER_NOTEXITED)))
                .stores(storesRepository.findById(request.getStoreId())
                        .orElseThrow(()-> new ThrowException(ErrorCode.STORE_NOTEXITED)))
                .build();

        return ordersMapper.toOrdersResponse(ordersRepository.save(order));
    }

    public OrdersResponse updateOrder(Integer orderId, OrdersUpdateRequest request) {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(()-> new ThrowException(ErrorCode.ORDERS_NOTEXITED));

        if(request.getDeliveryFee()!=null)
            order.setDeliveryFee(request.getDeliveryFee());
        if(request.getTotalPrice()!=null)
            order.setTotalPrice(request.getTotalPrice());
        if(request.getPaymentMethod()!=null)
            order.setPaymentMethod(request.getPaymentMethod());
        if(request.getStatus()!=null)
            order.setStatus(request.getStatus());

        return ordersMapper.toOrdersResponse(ordersRepository.save(order));
    }

    public void deleteOrder(Integer orderId) {
        ordersRepository.deleteById(orderId);
    }


}
