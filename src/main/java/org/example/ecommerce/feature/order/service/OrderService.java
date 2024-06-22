package org.example.ecommerce.feature.order.service;

//region imports
import org.example.ecommerce.database.models.WebOrder;
import org.example.ecommerce.database.repository.WebOrderRepository;
import org.example.ecommerce.feature.order.model.WebOrderResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
//endregion

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
            if (webOrders.isEmpty()) {
                logger.error("OrderService => getOrdersByUserId => No Orders Found for {}", userId);
                return new ArrayList<>();
            }
            logger.info("OrderService => getOrdersByUserId => Found {} Orders", webOrders.size());
            return webOrders.stream().map(webOrder -> new WebOrderResponseDTO(
                    webOrder.getUser(),
                    webOrder.getAddress(),
                    webOrder.getQuantities()
                    ))
                    .toList();
        } catch (Exception e) {
            logger.error("OrderService => getOrdersByUserId => Error {}", e.getMessage());
            return new ArrayList<>();
        }
    }
    //endregion
}
