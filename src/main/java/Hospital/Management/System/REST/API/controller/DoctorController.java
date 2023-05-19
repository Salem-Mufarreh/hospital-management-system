package Hospital.Management.System.REST.API.controller;

import Hospital.Management.System.REST.API.dto.DoctorDTO;
import Hospital.Management.System.REST.API.entity.Doctor;
import Hospital.Management.System.REST.API.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    private DoctorService _DoctorService;

    public DoctorController(DoctorService doctorService){ this._DoctorService = doctorService;}

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(_DoctorService.getDoctorById(id));
    }


}
