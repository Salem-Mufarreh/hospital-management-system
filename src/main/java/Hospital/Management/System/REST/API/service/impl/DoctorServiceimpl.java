package Hospital.Management.System.REST.API.service.impl;

import Hospital.Management.System.REST.API.dto.DoctorDTO;
import Hospital.Management.System.REST.API.entity.Doctor;
import Hospital.Management.System.REST.API.exception.ResourceNotFoundException;
import Hospital.Management.System.REST.API.repository.DoctorRepository;
import Hospital.Management.System.REST.API.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceimpl implements DoctorService {
    private final DoctorRepository _doctorRepo;

    public DoctorServiceimpl(DoctorRepository doctorRepo) {
        _doctorRepo = doctorRepo;
    }

    @Override
    public DoctorDTO getDoctorById(Long id) {
        Doctor doctor = _doctorRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", id));

        return mapToDTO(doctor);
    }

    @Override
    public List<DoctorDTO> getAllDoctors() {
        return _doctorRepo.findAll().stream().map(doctor -> mapToDTO(doctor)).collect(Collectors.toList());
    }

    @Override
    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = mapToEntity(doctorDTO);
        Doctor newdoctor =_doctorRepo.save(doctor);
        return mapToDTO(newdoctor);
    }

    @Override
    public DoctorDTO updateDoctor(Long id, DoctorDTO doctorDTO) {
        Doctor doctor = _doctorRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor", "id",id));
        doctor.setPhoneNumber(doctorDTO.getPhoneNumber());
        doctor.setName(doctorDTO.getName());
        doctor.setEmail(doctorDTO.getEmail());
        doctor.setSpecialty(doctorDTO.getSpecialty());
        _doctorRepo.save(doctor);

        return mapToDTO(doctor);
    }

    @Override
    public void deleteDoctor(Long id) {
        Doctor doctor = _doctorRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor","id",id));
        _doctorRepo.delete(doctor);
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
