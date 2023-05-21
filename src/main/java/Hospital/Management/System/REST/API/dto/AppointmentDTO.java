package Hospital.Management.System.REST.API.dto;

import lombok.Data;

import java.sql.Time;
import java.time.LocalDateTime;
@Data
public class AppointmentDTO {
    private Long AppointmentId;
    private LocalDateTime Date;
    private Time StartTime;
    private Time EndTime;
    private String Type;
    private String Status;
    private LocalDateTime CreationDate;
    private Long DoctorId;
    private Long PatientId;
}
