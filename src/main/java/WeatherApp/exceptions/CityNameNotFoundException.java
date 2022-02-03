package WeatherApp.exceptions;

public class CityNameNotFoundException extends Exception {

    public CityNameNotFoundException(String cityName) {
        super("Provided city not found: " + cityName);
    }

}
