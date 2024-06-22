package org.example.ecommerce.feature.order.service;

import org.example.ecommerce.feature.order.model.WebOrderResponseDTO;

import java.util.List;

public interface IOrderService {
    List<WebOrderResponseDTO> getOrdersByUserId(Long userId);
}
