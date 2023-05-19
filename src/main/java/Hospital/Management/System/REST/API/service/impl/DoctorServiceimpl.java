package Hospital.Management.System.REST.API.service.impl;

import Hospital.Management.System.REST.API.dto.DoctorDTO;
import Hospital.Management.System.REST.API.entity.Doctor;
import Hospital.Management.System.REST.API.exception.ResourceNotFoundException;
import Hospital.Management.System.REST.API.repository.DoctorRepository;
import Hospital.Management.System.REST.API.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceimpl implements DoctorService {
    private final DoctorRepository _doctorRepo;

    public DoctorServiceimpl(DoctorRepository doctorRepo) {
        _doctorRepo = doctorRepo;
    }

    @Override
    public DoctorDTO getDoctorById(Long id) {
        Doctor doctor = _doctorRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        return mapToDTO(doctor);
    }

    @Override
    public List<DoctorDTO> getAllDoctors() {
        return null;
    }

    @Override
    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
        return null;
    }

    @Override
    public DoctorDTO updateDoctor(Long id, DoctorDTO doctorDTO) {
        return null;
    }

    @Override
    public void deleteDoctor(Long id) {

    }
    public DoctorDTO mapToDTO (Doctor doctor){
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setDoctorId(doctor.getDoctorId());
        doctorDTO.setName(doctor.getName());
        doctorDTO.setEmail(doctor.getEmail());
        doctorDTO.setSpecialty(doctor.getSpecialty());
        doctorDTO.setPhoneNumber(doctor.getPhoneNumber());
        return doctorDTO;
    }

    public Doctor mapToEntity(DoctorDTO dto){
        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setDoctorId(dto.getDoctorId());
        doctor.setEmail(dto.getEmail());
        doctor.setSpecialty(doctor.getSpecialty());
        doctor.setPhoneNumber(doctor.getPhoneNumber());
        return doctor;
    }
}
