package WeatherApp.service;

import WeatherApp.api.WeatherApi;
import WeatherApp.api.WeatherAppController;
import WeatherApp.dao.WeatherRepository;
import WeatherApp.exceptions.CityNameNotFoundException;
import WeatherApp.model.CityWeatherData;
import WeatherApp.model.CityWeatherReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public Optional<CityWeatherReport> findById(long id) {
        return weatherRepository.findById(id);
    }

    public Iterable<CityWeatherReport> findAll() {
        return weatherRepository.findAll();
    }


    public CityWeatherReport saveCity(String cityName) throws CityNameNotFoundException {

        CityWeatherReport cityWeatherReport = getCityWeatherReport(cityName);

        Iterable<CityWeatherReport> allCities = weatherRepository.findAll();

        boolean cityAlreadyExists = false;

        for (CityWeatherReport city : allCities) {
            if (city.getCityName().equals(cityWeatherReport.getCityName())) {
                city.setUpdatedAt(cityWeatherReport.getUpdatedAt());
                city.setTemperature(cityWeatherReport.getTemperature());
                city.setHumidity(cityWeatherReport.getHumidity());
                city.setWind(cityWeatherReport.getWind());
                cityWeatherReport = weatherRepository.save(city);
                cityAlreadyExists = true;
                break;
            }
        }
        if (!cityAlreadyExists) {
            cityWeatherReport = weatherRepository.save(cityWeatherReport);
        }
        return cityWeatherReport;
    }


    public void deleteById(Long id) {
        weatherRepository.deleteById(id);
    }



    public CityWeatherReport getCityWeatherReport(String name) throws CityNameNotFoundException {

        try {
            WeatherApi api = new WeatherApi();
            CityWeatherData weatherData = api.getCurrentWeatherData(name);

            CityWeatherReport weatherReport = new CityWeatherReport();
            weatherReport.setCityName(weatherData.getName());
            weatherReport.setDateString(weatherData.getDt());
            weatherReport.setTemperature(weatherData.getMain().getTemp());
            weatherReport.setHumidity(weatherData.getMain().getHumidity());
            weatherReport.setWind(weatherData.getWind().getSpeed());

            return weatherReport;
        } catch (CityNameNotFoundException e) {
            throw new CityNameNotFoundException(name);
        }
    }


    @Scheduled(fixedRate = 15 * 60 * 1000) // every 15 minutes
//    @Scheduled(fixedRate = 3000) // every 3 seconds
    public void updateCitiesInDatabase() throws CityNameNotFoundException {

        Logger log = LoggerFactory.getLogger(WeatherAppController.class);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        log.info("Weather reports for cities were updated at {}", dateFormat.format(new Date()));

        Iterable<CityWeatherReport> allCities = findAll();

        for (CityWeatherReport city : allCities) {
            saveCity(city.getCityName());
        }
    }
}
