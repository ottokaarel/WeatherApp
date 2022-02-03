package WeatherApp.api;

import WeatherApp.exceptions.CityNameNotFoundException;
import WeatherApp.model.CityWeatherReport;
import WeatherApp.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
@RequestMapping("/api/weather")
public class WeatherAppController {

    public WeatherService weatherService;

    @Autowired
    public WeatherAppController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }


    @GetMapping( "")
    public Iterable<CityWeatherReport> getAllCities() {
        return weatherService.findAll();
    }


    @GetMapping("/{id}")
    public Optional<CityWeatherReport> getCity(@PathVariable Long id) {
        return weatherService.findById(id);
    }


    @PostMapping("/{cityName}")
    @ResponseStatus(HttpStatus.CREATED)
    public CityWeatherReport saveCity(@PathVariable String cityName) throws CityNameNotFoundException {
        return weatherService.saveCity(cityName);
    }


    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable Long id) {
        weatherService.deleteById(id);
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleErrors(Exception e) {
        return e.getMessage();
    }
}
