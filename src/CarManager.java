import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Car {
    private int id;
    private String make;
    private String model;
    private int yearOfManufacture;
    private String color;
    private double price;
    private String registrationNumber;

    public Car(int id, String make, String model, int yearOfManufacture, String color, double price, String registrationNumber) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.color = color;
        this.price = price;
        this.registrationNumber = registrationNumber;
    }

    // Getter methods for properties

    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }
}

public class CarManager {
    public static void main(String[] args) {
        // Create an array of Car objects
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(1, "Mercedes-Benz", "C300", 2018, "Black", 44850, "ABC123"));
        cars.add(new Car(2, "Honda", "Civic", 2019, "Red", 22000, "XYZ456"));
        cars.add(new Car(3, "Ford", "Focus", 2018, "Silver", 19000, "LMN789"));
        cars.add(new Car(4, "VW", "Golf", 2019, "Black", 17000, "VN689"));

        // Define criteria
        String brandFilter = "Mercedes-Benz";
        String modelFilter = "Golf";
        int yearsFilter = 3;
        int yearOfManufactureFilter = 2018;
        double priceFilter = 18000;

        // Filter and save cars to different files
        saveCarsByBrand(cars, brandFilter);
        saveCarsByModelAndAge(cars, modelFilter, yearsFilter);
        saveCarsByYearAndPrice(cars, yearOfManufactureFilter, priceFilter);
    }

    public static void saveCarsByBrand(List<Car> cars, String brand) {
        List<Car> filteredCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.getMake().equalsIgnoreCase(brand)) {
                filteredCars.add(car);
            }
        }
        saveToFile(filteredCars, "CarsByBrand_" + brand + ".txt");
    }

    public static void saveCarsByModelAndAge(List<Car> cars, String model, int years) {
        List<Car> filteredCars = new ArrayList<>();
        int currentYear = 2023;
        for (Car car : cars) {
            if (car.getModel().equalsIgnoreCase(model) && (currentYear - car.getYearOfManufacture()) > years) {
                filteredCars.add(car);
            }
        }
        saveToFile(filteredCars, "CarsByModelAndAge_" + model + "_" + years + "_Years.txt");
    }

    public static void saveCarsByYearAndPrice(List<Car> cars, int year, double price) {
        List<Car> filteredCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.getYearOfManufacture() == year && car.getPrice() > price) {
                filteredCars.add(car);
            }
        }
        saveToFile(filteredCars, "CarsByYearAndPrice_" + year + "_PriceAbove_" + price + ".txt");
    }

    public static void saveToFile(List<Car> cars, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Car car : cars) {
                writer.println(car.getId() + ", " + car.getMake() + ", " + car.getModel() + ", " +
                        car.getYearOfManufacture() + ", " + car.getColor() + ", " + car.getPrice() + ", " +
                        car.getRegistrationNumber());
            }
            System.out.println("Cars saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
