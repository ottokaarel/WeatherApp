package WeatherApp.model;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherData {

    public String name;
    public int dt;
    public MainWeatherProperties main;
    public WindProperties wind;


}
