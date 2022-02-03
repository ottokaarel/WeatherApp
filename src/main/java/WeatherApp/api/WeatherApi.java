package WeatherApp.api;


import WeatherApp.exceptions.CityNameNotFoundException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import WeatherApp.model.CurrentWeatherData;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;


import static com.sun.jersey.api.client.Client.create;
import static com.sun.jersey.api.json.JSONConfiguration.FEATURE_POJO_MAPPING;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;


public class WeatherApi {

    private static final Client client = getConfiguredClient();


    private static Client getConfiguredClient() {

        ClientConfig config = new DefaultClientConfig();
        config.getClasses().add(JacksonJaxbJsonProvider.class);
        config.getFeatures().put(FEATURE_POJO_MAPPING, true);

        return create(config);
    }


    public CurrentWeatherData getCurrentWeatherData(String cityName) throws CityNameNotFoundException {

        ClientResponse response = client.resource("https://api.openweathermap.org/data/2.5/weather")
                .queryParam("appid", "72b6f0ef3716a3ee22a4f033d5cf51e2")
                .queryParam("q", cityName)
                .queryParam("units", "metric")
                .get(ClientResponse.class);

        if (response.getStatus() == HTTP_NOT_FOUND) {
            throw new CityNameNotFoundException(cityName);
        }

        return response.getEntity(CurrentWeatherData.class);
    }
}
