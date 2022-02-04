package WeatherApp.exceptions;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(Long id) {
        super("Provided id not found: " + id);
    }

}
