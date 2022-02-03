package WeatherApp.api;


import WeatherApp.exceptions.CityNameNotFoundException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import WeatherApp.model.CityWeatherData;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


import static com.sun.jersey.api.client.Client.create;
import static com.sun.jersey.api.json.JSONConfiguration.FEATURE_POJO_MAPPING;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

@Component
public class WeatherApi {

    private static final Client client = getConfiguredClient();

    @Autowired
    private Environment env;

    public WeatherApi(Environment env) {
        this.env = env;
    }

    private static Client getConfiguredClient() {

        ClientConfig config = new DefaultClientConfig();
        config.getClasses().add(JacksonJaxbJsonProvider.class);
        config.getFeatures().put(FEATURE_POJO_MAPPING, true);

        return create(config);
    }


    public CityWeatherData getCurrentWeatherData(String cityName) throws CityNameNotFoundException {


        ClientResponse response = client.resource("https://api.openweathermap.org/data/2.5/weather")
                .queryParam("appid", env.getProperty("owm.apikey"))
                .queryParam("q", cityName)
                .queryParam("units", "metric")
                .get(ClientResponse.class);

        if (response.getStatus() == HTTP_NOT_FOUND) {
            throw new CityNameNotFoundException(cityName);
        }

        return response.getEntity(CityWeatherData.class);
    }
}
