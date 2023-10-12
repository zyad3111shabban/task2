import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarDatabase {
    private List<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
        System.out.println(car.toString());
    }

    
    
    public List<Car> filterByMake(String make) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.getMake().equalsIgnoreCase(make)) {
                result.add(car);
            }
        }
        return result;
    }

    public List<Car> filterByModelAndYears(String model, int n) {
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.getModel().equalsIgnoreCase(model) && currentYear - car.getYearOfManufacture() > n) {
                result.add(car);
            }
        }
        return result;
    }

    public List<Car> filterByYearAndPrice(int year, double price) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.getYearOfManufacture() == year && car.getPrice() > price) {
                result.add(car);
            }
        }
        return result;
    }

    public void saveListToFile(List<Car> list, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Car car : list) {
                writer.write(car.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CarDatabase database = new CarDatabase();
        // Add cars to the database (for this example, I'm adding dummy data)
        database.addCar(new Car(1, "Toyota", "Corolla", 2015, "Red", 15000, "1234XYZ"));
        database.addCar(new Car(2, "Toyota", "Camry", 2017, "Blue", 20000, "5678ABC"));
        database.addCar(new Car(3, "Volkswagen", "Golf", 2014, "Blue", 25000, "MF4666"));
        
        
        // ... add more cars as needed

        // Save lists to files based on filters
        database.saveListToFile(database.filterByMake("Toyota"), "ToyotaCars.txt");
        database.saveListToFile(database.filterByModelAndYears("Corolla", 5), "OldCorollaCars.txt");
        database.saveListToFile(database.filterByYearAndPrice(2017, 19000), "Expensive2017Cars.txt");
    }
}