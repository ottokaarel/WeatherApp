package WeatherApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cities")
public class WeatherReport {

    @Id
    @SequenceGenerator(name = "my_seq", sequenceName = "seq1", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    public Long id;

    @NotNull
    @Size(min = 2)
    @Column(name = "cityName")
    public String cityName;

    @NotNull
    @Column(name = "updatedAt")
    public String updatedAt;

    @NotNull
    public Double temperature;

    @NotNull
    public int humidity;

    @NotNull
    public Double wind;


}
