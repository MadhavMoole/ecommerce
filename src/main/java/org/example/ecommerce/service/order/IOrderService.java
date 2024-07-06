package org.example.ecommerce.service.order;

import org.example.ecommerce.dto.order.WebOrderResponseDTO;

import java.util.List;

public interface IOrderService {
    List<WebOrderResponseDTO> getOrdersByUserId(Long userId);
}
