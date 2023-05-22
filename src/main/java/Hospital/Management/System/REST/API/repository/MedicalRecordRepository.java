package Hospital.Management.System.REST.API.repository;

import Hospital.Management.System.REST.API.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    @Query("select a from MedicalRecord a where a.Patient.PatientId = :patientId")
    List<MedicalRecord> getMedicalRecordByPatientId (@Param("patientId") Long patientId);
    @Query("select a from MedicalRecord a where a.Doctor.DoctorId = :doctorId")
    List<MedicalRecord> getMedicalRecordByDoctorId(@Param("doctorId")Long doctorId);
    @Query("select a from MedicalRecord a where a.Appointment.AppointmentId = :appointmentId")
    MedicalRecord getMedicalRecordByAppointment(@Param("appointmentId") Long appointmentId);

}
