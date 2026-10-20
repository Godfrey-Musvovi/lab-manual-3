public class Temperature {
    private final double kelvin;   // internal representation

    public Temperature(double kelvin) {
        if (kelvin < 0) {
            throw new IllegalArgumentException("Temperature below absolute zero");
        }
        this.kelvin = kelvin;
    }

    // Factory method: create from Celsius
    public static Temperature fromCelsius(double celsius) {
        return new Temperature(celsius + 273.15);
    }

    public double getKelvin() { return kelvin; }

    public double getCelsius() { return kelvin - 273.15; }

    public double getFahrenheit() { return getCelsius() * 9 / 5 + 32; }

    public static void main(String[] args) {
        Temperature t = new Temperature(300);
        System.out.printf("Kelvin: %.2f%n", t.getKelvin());
        System.out.printf("Celsius: %.2f%n", t.getCelsius());
        System.out.printf("Fahrenheit: %.2f%n", t.getFahrenheit());

        Temperature boiling = Temperature.fromCelsius(100);
        System.out.printf("100 C = %.2f F%n", boiling.getFahrenheit());

        try {
            new Temperature(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
