package bg.sofia.uni.fmi.mjt.restaurant;

public class Chef extends Thread {

    private final int id;
    private int numberOfMealsCooked;
    private final Restaurant restaurant;

    public Chef(int id, Restaurant restaurant) {
        this.id = id;
        this.restaurant = restaurant;
        numberOfMealsCooked = 0;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Order nextOrder;
                synchronized (restaurant) {
                    nextOrder = restaurant.nextOrder();
                    if (nextOrder == null) {
                        System.out.println("Chef "+id+" is waiting for an order");
                        restaurant.wait();
                    }
                }
                if (nextOrder != null) {
                    Thread.sleep(nextOrder.meal().getCookingTime());
                    System.out.println(nextOrder.meal()+ " is cooked by Chef "+id);
                    numberOfMealsCooked++;
                }
            }
        } catch (
                InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    /**
     * Returns the total number of meals that this chef has cooked.
     **/
    public int getTotalCookedMeals() {
        return numberOfMealsCooked;
    }

    @Override
    public long getId() {
        return id;
    }
}