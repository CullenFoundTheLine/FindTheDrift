import java.util.*;

public class Garage {
    private List<Car> ownedCars = new ArrayList<>();
    private String owner;

    public Garage(String owner) {
        this.owner = owner;
    }

    public void addCar(Car car) {
        ownedCars.add(car);
    }

    public void removeCar(Car car) {
        ownedCars.remove(car);
    }

    public List<Car> getOwnedCars() {
        return ownedCars;
    }

    public Car getCarByModel(String model) {
        for (Car car : ownedCars) {
            if (car.getModelName().equalsIgnoreCase(model)) {
                return car;
            }
        }
        return null;
    }

    public void showGarage() {
        System.out.println("Garage for: " + owner);
        for (Car car : ownedCars) {
            System.out.println("- " + car.getModelName() + " (" + car.getType() + ")");
        }
    }
}
