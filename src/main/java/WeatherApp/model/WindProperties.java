package WeatherApp.model;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WindProperties {

    public Double speed;
    public Integer deg;

}
