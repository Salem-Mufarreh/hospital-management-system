package Hospital.Management.System.REST.API.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Doctor implements Serializable {
@Id
@GeneratedValue
private Long DoctorId;
private String Name;
private String Password;
private String Email;
private Long PhoneNumber;
private Boolean isActive;
private String DepartmentCode;
private String Specialty;


}
