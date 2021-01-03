package bg.sofia.uni.fmi.mjt.restaurant;

import java.util.*;

public class MJTDiningPlace implements Restaurant {

    private final Queue<Order> orders;
    private final Chef[] chefs;
    private int ordersCounter;
    private boolean isClose = false;

    public MJTDiningPlace(int numberOfChefs) {
        orders = new PriorityQueue<>((o1, o2) -> {
            if (o1.customer().hasVipCard() == o2.customer().hasVipCard()) {
                return Integer.compare(o2.meal().getCookingTime(), o1.meal().getCookingTime());
            }
            if (o1.customer().hasVipCard()) return 0;
            else return 1;
        });

        chefs = new Chef[numberOfChefs];
        for (int i = 0; i < numberOfChefs; i++) {
            chefs[i] = new Chef(i, this);
        }
    }

    @Override
    public synchronized void submitOrder(Order order) {
        orders.add(order);
        System.out.println("New order of " + order.meal());
        ordersCounter++;
        notifyAll();

    }

    @Override
    public synchronized Order nextOrder() {
        return orders.poll();
    }

    @Override
    public int getOrdersCount() {
        return ordersCounter;
    }

    @Override
    public Chef[] getChefs() {
        return chefs;
    }

    @Override
    public void close() {
        for (Chef chef:chefs) {
            chef.interrupt();
        }
    }

}
