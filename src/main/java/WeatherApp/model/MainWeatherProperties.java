package WeatherApp.model;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainWeatherProperties {

    public Double temp;
    public Integer humidity;

}
