package Hospital.Management.System.REST.API.repository;

import Hospital.Management.System.REST.API.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
