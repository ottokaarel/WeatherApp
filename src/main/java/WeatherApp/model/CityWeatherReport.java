package WeatherApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cities")
@Entity
public class CityWeatherReport {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long id;

    @NotNull
    @Size(min = 2)
    @Column(name = "city_name")
    public String cityName;

    @NotNull
    @Column(name = "updated_at")
    public String updatedAt;

    @NotNull
    @Column(name = "temperature")
    public Double temperature;

    @NotNull
    @Column(name = "humidity")
    public int humidity;

    @NotNull
    @Column(name = "wind")
    public Double wind;

    public void setDateString(long unixTimestamp) {
        updatedAt = getFormattedDate(unixTimestamp);
    }

    private String getFormattedDate(long unixTimestamp) {
        Date date = new Date();
        date.setTime(unixTimestamp * 1000);

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}
