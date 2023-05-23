package Hospital.Management.System.REST.API.service.impl;

import Hospital.Management.System.REST.API.dto.DoctorDTO;
import Hospital.Management.System.REST.API.entity.Doctor;
import Hospital.Management.System.REST.API.exception.ResourceNotFoundException;
import Hospital.Management.System.REST.API.repository.DoctorRepository;
import Hospital.Management.System.REST.API.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository _doctorRepo;

    public DoctorServiceImpl(DoctorRepository doctorRepo) {
        _doctorRepo = doctorRepo;
    }
    /* select doctor from id if it doesn't exist return 404 error*/
    @Override
    public DoctorDTO getDoctorById(Long id) {
        Doctor doctor = _doctorRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", id));

        return mapToDTO(doctor);
    }
    /* get all doctors */
    @Override
    public List<DoctorDTO> getAllDoctors() {
        return _doctorRepo.findAll().stream().map(doctor -> mapToDTO(doctor)).collect(Collectors.toList());
    }
    /* create new doctor*/
    @Override
    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = mapToEntity(doctorDTO);
        /*generate a random number */
        doctor.setDoctorId(new Random().nextLong());
        Doctor newdoctor =_doctorRepo.save(doctor);
        return mapToDTO(newdoctor);
    }
    /*update doctor*/
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
        doctor.setDoctorId(dto.getDoctorId());
        doctor.setName(dto.getName());
        doctor.setDoctorId(dto.getDoctorId());
        doctor.setEmail(dto.getEmail());
        doctor.setSpecialty(dto.getSpecialty());
        doctor.setPhoneNumber(dto.getPhoneNumber());
        return doctor;
    }
}
