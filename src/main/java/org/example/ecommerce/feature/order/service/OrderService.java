package org.example.ecommerce.feature.order.service;

import org.example.ecommerce.database.models.WebOrder;
import org.example.ecommerce.database.repository.WebOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private WebOrderRepository webOrderRepository;

    @Autowired
    public void setWebOrderRepository(WebOrderRepository webOrderRepository) {
        this.webOrderRepository = webOrderRepository;
    }

    public List<WebOrder> getOrdersByUserId(long userId) {
        return webOrderRepository.findOrderByUserId(userId);
    }
}
