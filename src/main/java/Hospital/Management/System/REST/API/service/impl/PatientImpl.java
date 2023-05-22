package Hospital.Management.System.REST.API.service.impl;

import Hospital.Management.System.REST.API.dto.PatientDTO;
import Hospital.Management.System.REST.API.entity.Patient;
import Hospital.Management.System.REST.API.exception.ResourceNotFoundException;
import Hospital.Management.System.REST.API.repository.PatientRepository;
import Hospital.Management.System.REST.API.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PatientImpl implements PatientService {
    private final PatientRepository _PatientRepository;

    public PatientImpl(PatientRepository patientRepository) {
        _PatientRepository = patientRepository;
    }

    @Override
    public PatientDTO CreatePatient(PatientDTO patientDTO) {
        Patient patient = toPatient(patientDTO);
        patient.setPatientId(new Random().nextLong());
        Patient newPatient= _PatientRepository.save(patient);
        return toPatientDTO(newPatient);
    }

    @Override
    public PatientDTO GetPatientById(Long id) {
        Patient patient = new Patient();
        if(id != null){
            patient =_PatientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient","id",id));
        }
        return toPatientDTO(patient);
    }

    @Override
    public List<PatientDTO> GetAllPatients() {
        List<PatientDTO> Patients = _PatientRepository.findAll().stream().map(d -> toPatientDTO(d)).toList();
        return Patients;
    }

    @Override
    public PatientDTO UpdatePatient(Long id, PatientDTO patientDTO) {
        Patient patient = _PatientRepository.findById(id).orElseThrow();
        patient = toPatient(patientDTO);
        _PatientRepository.save(patient);
        return toPatientDTO(patient);

    }

    @Override
    public void DeletePatient(Long id) {
        Patient patient = _PatientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient","id",id));
        _PatientRepository.delete(patient);

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
