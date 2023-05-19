package Hospital.Management.System.REST.API.dto;

import lombok.Data;

@Data
public class DoctorDTO {
    private Long DoctorId;
    private String Name;
    private String Specialty;
    private String Email;
    private Long PhoneNumber;

}
