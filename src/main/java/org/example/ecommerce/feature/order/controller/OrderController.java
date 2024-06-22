package org.example.ecommerce.feature.order.controller;

//region imports
import org.example.ecommerce.database.models.User;
import org.example.ecommerce.database.models.WebOrder;
import org.example.ecommerce.feature.order.model.WebOrderResponseDTO;
import org.example.ecommerce.feature.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//endregion

@RestController
@RequestMapping("/order/v1")
public class OrderController {

    //region declaration
    private final OrderService orderService;
    //endregion

    //region di
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    //endregion

    //region getOrdersByUserId
    @GetMapping
    public ResponseEntity<List<WebOrderResponseDTO>> getOrders(@AuthenticationPrincipal User user) {
        List<WebOrderResponseDTO> webOrders = orderService.getOrdersByUserId(user.getId());
        if (webOrders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(webOrders);
    }
    //endregion
}
