package Hospital.Management.System.REST.API.repository;

import Hospital.Management.System.REST.API.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
