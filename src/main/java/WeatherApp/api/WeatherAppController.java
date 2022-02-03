package WeatherApp.controller;

import WeatherApp.dao.WeatherDao;
import WeatherApp.exceptions.CityNameNotFoundException;
import WeatherApp.model.WeatherReport;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
public class WeatherAppController {



    public WeatherDao dao;

    public WeatherAppController(WeatherDao dao) {
        this.dao = dao;
    }


    @GetMapping("/{id}")
    public WeatherReport getCurrentWeather(@PathVariable Long id) throws CityNameNotFoundException {
        return dao.findById(5L);
    }

//    @GetMapping( "/api/weather")
//    public List<WeatherReport> getOrders() {
//        return dao.findAll();
//    }

//    @GetMapping("/api/weather/{id}")
//    public WeatherReport getCurrentWeather(@PathVariable Long id) throws CityNameNotFoundException {
//        return dao.findById(id);
//    }

//    @PostMapping("/api/weather")
//    @ResponseStatus(HttpStatus.OK)
//    public WeatherReport saveCityWeather(@RequestBody @Valid WeatherReport order) {
//        return dao.save(order);
//    }
//
//    @DeleteMapping("/api/weather/{id}")
//    public void deleteCity(@PathVariable Long id) {
//        dao.delete(id);
//    }

}
