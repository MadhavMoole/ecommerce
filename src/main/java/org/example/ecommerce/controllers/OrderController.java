package org.example.ecommerce.controllers;

import org.example.ecommerce.database.models.User;
import org.example.ecommerce.dto.order.WebOrderResponseDTO;
import org.example.ecommerce.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    //region getOrdersByUserId controller
    @GetMapping
    public ResponseEntity<List<WebOrderResponseDTO>> getOrders(@AuthenticationPrincipal User user) {
        List<WebOrderResponseDTO> webOrders = orderService.getOrdersByUserId(user.getId());
        return ResponseEntity.ok(webOrders);
    }
    //endregion
}
