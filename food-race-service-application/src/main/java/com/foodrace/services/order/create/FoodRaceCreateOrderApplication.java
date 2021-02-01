package main.java.com.foodrace.services.order.create;

import main.java.com.foodrace.services.order.create.service.OrderService;

/**
 * @author lokendrav
 *
 */
public class FoodRaceCreateOrderApplication {

	public static void main(String[] args) {
		OrderService service = new OrderService();
		service.triggerCreateOrderProcess();
	}
}
