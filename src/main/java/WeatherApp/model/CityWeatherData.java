package WeatherApp.model;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityWeatherData {

    public String name;
    public long dt;
    public MainWeatherProperties main;
    public WindProperties wind;


}
