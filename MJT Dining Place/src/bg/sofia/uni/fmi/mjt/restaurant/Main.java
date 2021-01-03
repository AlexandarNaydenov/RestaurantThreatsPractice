package bg.sofia.uni.fmi.mjt.restaurant;

import bg.sofia.uni.fmi.mjt.restaurant.customer.Customer;
import bg.sofia.uni.fmi.mjt.restaurant.customer.VipCustomer;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MJTDiningPlace diningPlace = new MJTDiningPlace(5);
        startingRestaurant(diningPlace);
        generateCostumers(diningPlace,100);
        //our working day will be 3000 millis
        sleep(3000);
        diningPlace.close();
        System.out.println('\n'+"All orders for today : "+diningPlace.getOrdersCount());
        allChefsOrders(diningPlace);
    }

    private static void startingRestaurant(Restaurant restaurant)
    {
        for (Chef chef: restaurant.getChefs()) {
            chef.start();
        }
    }

    private static void generateCostumers(Restaurant restaurant,int number)
    {
        for(int i=0;i<number;i++)
        {
            new Customer(restaurant).start();
        }
    }

    private static void allChefsOrders(Restaurant restaurant)
    {
        for (Chef chef: restaurant.getChefs()) {
            System.out.println("Chef number "+chef.getId()+" has "+chef.getTotalCookedMeals()+" orders");
        }
    }
}
