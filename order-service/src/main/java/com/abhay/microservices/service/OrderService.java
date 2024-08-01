package com.abhay.microservices.service;

import com.abhay.microservices.client.InventoryClient;
import com.abhay.microservices.dto.OrderRequest;
import com.abhay.microservices.model.Order;
import com.abhay.microservices.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    private final InventoryClient inventoryClient;
    public void placeOrder(OrderRequest orderRequest){
        var isProductInStock =inventoryClient.isInStock(orderRequest.skuCode(),orderRequest.quantity());
        //map OrderRequest to Order object
        if (isProductInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(order.getQuantity());
            orderRepo.save(order);
            }else{
                throw  new RuntimeException("product with skucode "+orderRequest.skuCode()+"is not in skuCode ");
            }
    }
}
