package app.vehiclemanagement.hr.models;


import app.vehiclemanagement.parameters.models.Country;
import app.vehiclemanagement.parameters.models.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String othername;

    @Formula(value = " concat(firstname, ' ', lastname) ")
    private String fullName;

    private String title;
    private String initials;
    private String personalIdentificationNumber;
    private String gender;
    private String maritalStatus;

    @ManyToOne
    @JoinColumn(name = "countryid", insertable = false, updatable = false)
    private Country country;
    private Integer countryid;

    @ManyToOne
    @JoinColumn(name = "stateid", insertable = false, updatable = false)
    private State state;
    private Integer stateid;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private String city;
    private String address;
    private String mobile;
    private String email;
}
