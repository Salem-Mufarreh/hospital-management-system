package Hospital.Management.System.REST.API.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Patient implements Serializable {
    @jakarta.persistence.Id
    @GeneratedValue
    private Long PatientId;
    private String Name;
    private Date DateOfBirth;
    private String Gender;
    private Long PhoneNumber;
    private String Email;
    private Date RegistrationDate;


}
