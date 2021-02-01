package main.java.com.foodrace.services.order.create.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import main.java.com.foodrace.services.order.create.model.LoggingDetailsModel;
import main.java.com.foodrace.services.order.create.utils.LoggingComponent;

/**
 * @author lokendrav
 *
 */
public class OrderService {

	Queue<LoggingDetailsModel> logQueue = null;

	/**
	 * @Def: Method to Trigger Create Order Process
	 * 
	 * @return
	 */
	public void triggerCreateOrderProcess() {
		logQueue = new LinkedList<LoggingDetailsModel>();
		logQueue.add(new LoggingDetailsModel("info", "STARTED: Trigger Create Order Process"));
		try {
			Map<Integer, String> menuItems = displayMenuItems();
			Map<String, Integer> orderBucket = new HashMap<>();
			Scanner in = new Scanner(System.in);
			int i = 0;
			while (i <= 2) {
				System.out.print("\nPlease enter what you want to order: ");
				int orderItem = in.nextInt();
				if (menuItems.containsKey(orderItem)) {
					String selectedFoodItem = menuItems.get(orderItem);
					System.out.print("You selection is " + selectedFoodItem + ", Please enter the quantity : ");
					int orderQuantity = in.nextInt();
					addOrderItem(selectedFoodItem, orderQuantity, orderBucket);
				} else {
					System.out.println("You entry was invalid, please select the food item from the menu: ");
					logQueue.add(new LoggingDetailsModel("warning", "User Entered an invalid Input"));
				}
				i++;
			}
			claculateTotalOrderPrice(orderBucket);
			in.close();
			logQueue.add(new LoggingDetailsModel("info", "COMPLETED: Trigger Create Order Process"));
		} catch (Exception e) {
			logQueue.add(new LoggingDetailsModel("severe", "Error While Placing order due to: " + e.getMessage()));
		}
		LoggingComponent.log(logQueue);

	}

	/**
	 * @Def: Method to Add Selected order item to the order bucket
	 * 
	 * @param selectedFoodItem
	 * @param orderQuantity
	 * @param orderBucket
	 * @return updated order bucket list
	 */
	private Map<String, Integer> addOrderItem(String selectedFoodItem, int orderQuantity,
			Map<String, Integer> orderBucket) {
		if (orderBucket.containsKey(selectedFoodItem)) {
			orderBucket.put(selectedFoodItem, orderBucket.get(selectedFoodItem) + orderQuantity);
		} else {
			orderBucket.put(selectedFoodItem, orderQuantity);
		}
		logQueue.add(new LoggingDetailsModel("info", "Added Item to Order Bucket".concat(orderBucket.toString())));
		return orderBucket;
	}

	/**
	 * @Def: Method to Display, Return Menu Items
	 * @return Menu Items
	 */
	public Map<Integer, String> displayMenuItems() {
		Map<Integer, String> menuItmes = new ConcurrentHashMap<Integer, String>();
		menuItmes.put(1, "Idly");
		menuItmes.put(2, "Dosa");
		menuItmes.put(3, "Poha");
		menuItmes.put(4, "Upma");
		menuItmes.put(5, "Sandwich");
		System.out.println("::: Food-Race Breakfast Menu Items :::");
		menuItmes.forEach((k, v) -> System.out.println((k + ". " + v)));
		return menuItmes;
	}

	/**
	 * @Def: Method to calculate the total price to be paid
	 * 
	 * @param orderBucket
	 * @return
	 */
	public int claculateTotalOrderPrice(Map<String, Integer> orderBucket) {
		int totalAmountToBePaid = 0;
		System.out.println("\n\t Your Order Items Are\n \t-------------------------\n");
		System.out.println("Food Item \t Quantity\t Amount \n--------- \t--------- \t----------\n");

		for (Map.Entry<String, Integer> orderItem : orderBucket.entrySet()) {
			totalAmountToBePaid += orderItem.getValue() * 20;
			int orderQuantity = orderItem.getValue();
			System.out.printf("%-20s %-10d %dx20=%d/- Rs \n", orderItem.getKey(), orderQuantity, orderQuantity,
					orderQuantity * 20);
		}
		System.out.println("\nTotal Amount to be paid = " + totalAmountToBePaid);
		logQueue.add(
				new LoggingDetailsModel("info", "Final Order Placed, Total Price to be paid: " + totalAmountToBePaid));
		return totalAmountToBePaid;

	}

}
