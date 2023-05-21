package Hospital.Management.System.REST.API.service;

import Hospital.Management.System.REST.API.dto.PatientDTO;
import Hospital.Management.System.REST.API.entity.Patient;

import java.util.List;

public interface PatientService {
    /*Patient and Patient DTO have the same values but still will use DTO*/
    PatientDTO CreatePatient(PatientDTO patientDTO);
    PatientDTO GetPatientById(Long id);
    List<PatientDTO> GetAllPatients();
    PatientDTO UpdatePatient(Long id, PatientDTO patient);
    void DeletePatient(Long id);
    PatientDTO toPatientDTO(Patient patient);
    Patient toPatient(PatientDTO patientDTO);
}
