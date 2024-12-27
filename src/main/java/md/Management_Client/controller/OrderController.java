package md.Management_Client.controller;

import md.Management_Client.entity.Order;
import md.Management_Client.exceptions.AlreadyExistsException;
import md.Management_Client.exceptions.NotFoundException;
import md.Management_Client.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public Order getOrderById(@PathVariable Long id) {
        if (!orderRepository.exists(id))
            throw new NotFoundException();
        return orderRepository.getOrderById(id);
    }

    @GetMapping("/orders/amount")
    public List<Order> getOrderByAmount(@RequestParam("amount") double amount) {
        return orderRepository.getOrderByAmount(amount);
    }

    @PostMapping("/orders")
    public void createOrder(@RequestBody Order order) {
        if(orderRepository.exists(order.getId()))
            throw new AlreadyExistsException();
        orderRepository.createOrder(order);
    }

    @PutMapping("/orders/{id}")
    public void updateOrder(@PathVariable Long id, @RequestBody Order order) {
        orderRepository.updateOrder(id, order);
    }

    @DeleteMapping("/orders/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderRepository.deleteOrder(id);
    }
}
