package Hospital.Management.System.REST.API.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PatientDTO {
    private Long PatientId;
    private String Name;
    private Date DateOfBirth;
    private String Gender;
    private Long PhoneNumber;
    private String Email;
    private Date RegistrationDate;
}
