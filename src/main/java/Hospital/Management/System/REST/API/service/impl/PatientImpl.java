package Hospital.Management.System.REST.API.service.impl;

import Hospital.Management.System.REST.API.dto.PatientDTO;
import Hospital.Management.System.REST.API.entity.Patient;
import Hospital.Management.System.REST.API.repository.PatientRepository;
import Hospital.Management.System.REST.API.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientImpl implements PatientService {
    private final PatientRepository _PatientRepository;

    public PatientImpl(PatientRepository patientRepository) {
        _PatientRepository = patientRepository;
    }

    @Override
    public PatientDTO CreatePatient(PatientDTO patientDTO) {
        Patient patient = toPatient(patientDTO);
        System.out.println(patient.toString());
        Patient newPatient= _PatientRepository.save(patient);
        System.out.println(newPatient);
        return toPatientDTO(newPatient);
    }

    @Override
    public PatientDTO GetPatientById(Long id) {
        return null;
    }

    @Override
    public List<PatientDTO> GetAllPatients() {
        return null;
    }

    @Override
    public PatientDTO UpdatePatient(Long id, PatientDTO patient) {
        return null;
    }

    @Override
    public void DeletePatient(Long id) {

    }

    public PatientDTO toPatientDTO(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setPatientId(patient.getPatientId());
        patientDTO.setName(patient.getName());
        patientDTO.setDateOfBirth(patient.getDateOfBirth());
        patientDTO.setGender(patient.getGender());
        patientDTO.setPhoneNumber(patient.getPhoneNumber());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setRegistrationDate(patient.getRegistrationDate());
        return patientDTO;
    }
    public Patient toPatient(PatientDTO patientDTO) {
        Patient patient = new Patient();
        patient.setPatientId(patientDTO.getPatientId());
        patient.setName(patientDTO.getName());
        patient.setDateOfBirth(patientDTO.getDateOfBirth());
        patient.setGender(patientDTO.getGender());
        patient.setPhoneNumber(patientDTO.getPhoneNumber());
        patient.setEmail(patientDTO.getEmail());
        patient.setRegistrationDate(patientDTO.getRegistrationDate());
        return patient;
    }
}
