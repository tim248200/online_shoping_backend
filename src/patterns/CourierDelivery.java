package patterns;

import interfaces.DeliveryStrategy;
import model.Order;

public class CourierDelivery implements DeliveryStrategy {
    @Override
    public double CalculateCost(Order order) {
        return 300.0;
    }

    @Override
    public void Deliver(Order order) {
        System.out.println("Заказ: " + order.getId() + "доставляется курьером");
    }
}