package org.example.ecommerce.service.order;

import org.example.ecommerce.database.models.WebOrder;
import org.example.ecommerce.database.repository.WebOrderRepository;
import org.example.ecommerce.dto.order.WebOrderResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    //region declaration
    private WebOrderRepository webOrderRepository;
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);
    //endregion

    //region di
    @Autowired
    public void setWebOrderRepository(WebOrderRepository webOrderRepository) {
        this.webOrderRepository = webOrderRepository;
    }
    //endregion

    //region getOrdersByUserId
    @Override
    public List<WebOrderResponseDTO> getOrdersByUserId(Long userId) {
        try{
            List<WebOrder> webOrders = webOrderRepository.findOrderByUserId(userId);
            if (!webOrders.isEmpty()) {
                return webOrders.stream()
                        .map(WebOrderResponseDTO::fromEntity)
                        .toList();
            }
        } catch (Exception e) {
            logger.error("OrderService => getOrdersByUserId => Error {}", e.getMessage());
        }
        return null;
    }
    //endregion
}
