package Hospital.Management.System.REST.API.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Appointments {
    @Id
    @GeneratedValue
    private int AppointmentId;
    private LocalDateTime Date;
    private Time StartTime;
    private Time EndTime;
    private String Type;
    private String Status;
    private LocalDateTime CreationDate;

    @ManyToOne
    @JoinColumn(name = "DoctorId")
    private Doctor Doctor;

    @ManyToOne
    @JoinColumn(name = "PatientId")
    private Patient Patient;
    



}
