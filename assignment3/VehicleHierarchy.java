package assignment3;

public class VehicleHierarchy {

    // Abstract base class for all vehicles
    public abstract static class Vehicle {
        private String make;
        private String model;
        private int year;

        // Constructor for Vehicle class
        public Vehicle(String make, String model, int year) {
            this.make = make;
            this.model = model;
            this.year = year;
        }
        // Method to display vehicle information
        public void displayInfo() {
            System.out.println(String.format(" Vehicle INFO: %d %s %s", year, make, model));
        }
    }

    // Subclass for cars
    public static class Car extends Vehicle {
        private int numDoors;

        // Constructor for Car class
        public Car(String make, String model, int year, int numDoors) {
            super(make, model, year);
            this.numDoors = numDoors;
        }

        // Override the displayInfo method to include number of doors
        @Override
        public void displayInfo() {
            super.displayInfo();
            System.out.println(String.format(" Number of Doors: %d", numDoors));
        }
    }

    // Subclass for motorcycles
    public static class Motorcycle extends Vehicle {
        private boolean hasSidecar;

        // Constructor for Motorcycle class
        public Motorcycle(String make, String model, int year, boolean hasSidecar) {
            super(make, model, year);
            this.hasSidecar = hasSidecar;
        }

        // Override the displayInfo method to include sidecar information
        @Override
        public void displayInfo() {
            super.displayInfo();
            System.out.println(String.format(" Has Sidecar: %b", hasSidecar));
        }
    }

    public static void main(String[] args) {
        // Create instances of different vehicle types
        Vehicle[] vehicles = new Vehicle[3];
        vehicles[0] = new Car("Toyota", "Camry", 2020, 4);
        vehicles[1] = new Car("Honda", "Civic", 2019, 4);
        vehicles[2] = new Motorcycle("Harley-Davidson", "Street 750", 2021, false);
        
        // Display information for each vehicle
        for (Vehicle vehicle : vehicles) {
            vehicle.displayInfo();
        }
    }
}
