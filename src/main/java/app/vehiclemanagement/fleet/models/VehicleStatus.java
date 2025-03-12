package app.vehiclemanagement.fleet.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class VehicleStatus  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;
    private String details;

    @ManyToOne
    @JoinColumn(name="vehicleid", insertable = false, updatable = false)
    private Vehicle vehicle;
    private Integer vehicleid;


    @OneToMany(mappedBy = "vehicleStatus")
    private List<Vehicle> vehicles;

}