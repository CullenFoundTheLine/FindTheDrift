import java.util.*;

public class CarCatalog {
    private List<Car> allCars = new ArrayList<>();

    public CarCatalog() {}

    public void addCar(Car car) {
        allCars.add(car);
    }

    public List<Car> getAllCars() {
        return allCars;
    }

    public Car findCarByModel(String model) {
        for (Car car : allCars) {
            if (car.getModelName().equalsIgnoreCase(model)) {
                return car;
            }
        }
        return null;
    }
}
