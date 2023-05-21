package Hospital.Management.System.REST.API.repository;

import Hospital.Management.System.REST.API.entity.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentsRepository extends JpaRepository<Appointments, Long> {
    @Query("select a from Appointments a where a.Doctor.DoctorId = :doctorId")
    List<Appointments> getAppointmentsByDoctorId(@Param("doctorId") Long doctorId);
    @Query("select a from Appointments a where a.Patient.PatientId = :patientId order by a.Date asc ")
    List<Appointments> getAppointmentsByPatientPatientIdOrderByDate(@Param("patientId") Long patientId);
}
