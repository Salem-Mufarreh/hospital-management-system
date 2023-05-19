package Hospital.Management.System.REST.API.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class MedicalRecord {
    @Id
    @GeneratedValue
    private Long MedicalRecordId;

    @ManyToOne
    @JoinColumn(name = "PatientId")
    private Patient Patient;

    @ManyToOne
    @JoinColumn(name = "DoctorId")
    private Doctor Doctor;

    private String Diagnosis;
    private String Treatment;


}
