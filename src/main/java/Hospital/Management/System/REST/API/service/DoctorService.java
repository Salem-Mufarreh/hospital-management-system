package Hospital.Management.System.REST.API.service;

import Hospital.Management.System.REST.API.dto.DoctorDTO;

import java.util.List;

public interface DoctorService {

    DoctorDTO getDoctorById(Long id);
    List<DoctorDTO> getAllDoctors();
    DoctorDTO createDoctor(DoctorDTO doctorDTO);
    DoctorDTO updateDoctor(Long id, DoctorDTO doctorDTO);
    void deleteDoctor(Long id);
}