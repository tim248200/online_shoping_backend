package interfaces;

import model.Order;

public interface DeliveryStrategy {
    double CalculateCost(Order order);
    void Deliver(Order order);
}
