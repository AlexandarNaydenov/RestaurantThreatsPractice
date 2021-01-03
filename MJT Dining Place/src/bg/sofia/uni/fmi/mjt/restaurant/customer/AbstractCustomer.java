package bg.sofia.uni.fmi.mjt.restaurant.customer;

import bg.sofia.uni.fmi.mjt.restaurant.Meal;
import bg.sofia.uni.fmi.mjt.restaurant.Order;
import bg.sofia.uni.fmi.mjt.restaurant.Restaurant;

public abstract class AbstractCustomer extends Thread {
    private final Restaurant restaurant;

    public AbstractCustomer(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            synchronized (restaurant) {
                restaurant.submitOrder(new Order(Meal.chooseFromMenu(), this));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public abstract boolean hasVipCard();

}