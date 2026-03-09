package assignment2;

public class TemperatureConverter {
    private static final double ABSOLUTE_ZERO_CELSIUS    = -273.15;
    private static final double ABSOLUTE_ZERO_FAHRENHEIT = -459.67;

    // Convert Celsius to Fahrenheit or Kelvin
    public static double convertCelsius(double celsius, String targetScale) {
        // Check for temperatures below absolute zero in Celsius
        if (celsius < ABSOLUTE_ZERO_CELSIUS) {
            throw new IllegalArgumentException("Temperature cannot be below absolute zero (-273.15°C).");
        }
        // Convert Celsius to the target scale
        switch (targetScale.toLowerCase()) {
            case "fahrenheit":
                return (celsius * 9 / 5) + 32;
            case "kelvin":
                return celsius - ABSOLUTE_ZERO_CELSIUS;
            default:
                throw new IllegalArgumentException("Invalid target scale. Use 'Fahrenheit' or 'Kelvin'.");
        }
    }

    // Convert Fahrenheit to Celsius or Kelvin
    public static double convertFahrenheit(double fahrenheit, String targetScale) {
        // Check for temperatures below absolute zero in Fahrenheit
        if (fahrenheit < ABSOLUTE_ZERO_FAHRENHEIT) {
            throw new IllegalArgumentException("Temperature cannot be below absolute zero (-459.67°F).");
        }
        // Convert Fahrenheit to the target scale
        switch (targetScale.toLowerCase()) {
            case "celsius":
                return (fahrenheit - 32) * 5 / 9;
            case "kelvin":
                return (fahrenheit - 32) * 5 / 9 - ABSOLUTE_ZERO_CELSIUS;
            default:
                throw new IllegalArgumentException("Invalid target scale. Use 'Celsius' or 'Kelvin'.");
        }
    }

    // Convert Kelvin to Celsius or Fahrenheit
    public static double convertKelvin(double kelvin, String targetScale) {
        // Check for temperatures below absolute zero in Kelvin
        if (kelvin < 0) {
            throw new IllegalArgumentException("Temperature cannot be below absolute zero (0 K).");
        }

        // Convert Kelvin to the target scale
        switch (targetScale.toLowerCase()) {
            case "celsius":
                return kelvin + ABSOLUTE_ZERO_CELSIUS;
            case "fahrenheit":
                return (kelvin + ABSOLUTE_ZERO_CELSIUS) * 9 / 5 + 32;
            default:
                throw new IllegalArgumentException("Invalid target scale. Use 'Celsius' or 'Fahrenheit'.");
        }
    }

    //Convert multiple temperatures from one scale to another
    public static void convertMultiple(double temperatures[], String sourceScale, String targetScale) {
        for (double temp : temperatures) {
            double convertedTemp;
            switch (sourceScale.toLowerCase()) {
                case "celsius":
                    convertedTemp = convertCelsius(temp, targetScale);
                    break;
                case "fahrenheit":
                    convertedTemp = convertFahrenheit(temp, targetScale);
                    break;
                case "kelvin":
                    convertedTemp = convertKelvin(temp, targetScale);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid source scale. Use 'Celsius', 'Fahrenheit', or 'Kelvin'.");
            }
            System.out.printf("  %.2f %s is equivalent to %.2f %s%n", temp, sourceScale, convertedTemp, targetScale);
        }
    }

    // Main method with test cases
    public static void main(String[] args) {
        System.out.println("\n Temperature Conversion Results:");
        
        // Single-Value Conversions
        System.out.println("\n Single-Value Conversions: \n");

        double f = convertCelsius(25.0, "Fahrenheit");
        double k = convertCelsius(25.0, "Kelvin");
        System.out.printf("  25.00°C is equivalent to %.2f°F and %.2fK%n", f, k);

        double c = convertFahrenheit(68.0, "Celsius");
        k = convertFahrenheit(68.0, "Kelvin");
        System.out.printf("  68.00°F is equivalent to %.2f°C and %.2fK%n", c, k);

        c = convertKelvin(300.0, "Celsius");
        f = convertKelvin(300.0, "Fahrenheit");
        System.out.printf("  300.00K is equivalent to %.2f°C and %.2f°F%n", c, f);

        //Validation Tests 
        System.out.println("\nValidation Tests: \n");

        try {
            convertCelsius(-300.0, "Fahrenheit");
        } catch (IllegalArgumentException e) {
            System.out.println("  " + e.getMessage());
        }
        
        try {
            convertKelvin(-5.0, "Celsius");
        } catch (IllegalArgumentException e) {
            System.out.println("  " + e.getMessage());
        }

        try {
            convertFahrenheit(-500.0, "Kelvin");
        } catch (IllegalArgumentException e) {
            System.out.println("  " + e.getMessage());
        }

        // Convert Multiple Tests
        System.out.println("\n Convert Multiple Tests: \n");
        
        double[] celsiusTemps = {0.0, 25.0, 37.0, 100.0};
        convertMultiple(celsiusTemps, "Celsius", "Fahrenheit");

        double[] fahrenheitTemps = {32.0, 68.0, 98.6, 212.0};
        convertMultiple(fahrenheitTemps, "Fahrenheit", "Celsius");

        double[] kelvinTemps = {0.0, 273.15, 300.0, 373.15};
        convertMultiple(kelvinTemps, "Kelvin", "Celsius");
    }
 }
