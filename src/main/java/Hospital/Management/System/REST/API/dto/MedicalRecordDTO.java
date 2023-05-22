package Hospital.Management.System.REST.API.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MedicalRecordDTO {
    private Long MedicalRecordId;
    private Long PatientId;
    private Long DoctorId;
    private Long AppointmentId;
    private String Diagnosis;
    private String Treatment;
    private LocalDateTime lastModifiedDate;


}
