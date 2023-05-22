package Hospital.Management.System.REST.API.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class MedicalRecord  implements Serializable {
    @Id
    @GeneratedValue
    private Long MedicalRecordId;

    @ManyToOne
    @JoinColumn(name = "PatientId")
    private Patient Patient;

    @ManyToOne
    @JoinColumn(name = "DoctorId")
    private Doctor Doctor;

    @OneToOne
    @JoinColumn(name = "AppointmentId")
    private Appointments Appointment;

    private String Diagnosis;
    private String Treatment;
    private LocalDateTime lastModifiedDate;


}
