package WeatherApp.dao;

import WeatherApp.model.CityWeatherReport;
import org.springframework.data.repository.CrudRepository;



public interface WeatherRepository extends CrudRepository<CityWeatherReport, Long> {}