package Hospital.Management.System.REST.API.controller;

import Hospital.Management.System.REST.API.dto.DoctorDTO;
import Hospital.Management.System.REST.API.entity.Doctor;
import Hospital.Management.System.REST.API.service.DoctorService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    private DoctorService _DoctorService;
    private final Logger log = LoggerFactory.getLogger(DoctorController.class);

    public DoctorController(DoctorService doctorService){ this._DoctorService = doctorService;}

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(_DoctorService.getDoctorById(id));
    }
    @GetMapping("")
    public ResponseEntity<List<DoctorDTO>> listDoctors(){

        return ResponseEntity.ok(_DoctorService.getAllDoctors());
    }
    @PostMapping
    public ResponseEntity<DoctorDTO> createDoctor(
            @Valid @RequestBody DoctorDTO doctorDTO){
        if(doctorDTO.getDoctorId() == null){
            log.error("empty id");
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
        else {
            log.info("Doctor was Created ");
            return new ResponseEntity(_DoctorService.createDoctor(doctorDTO), HttpStatus.CREATED);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> updateDoctor(
            @Valid @RequestBody DoctorDTO doctorDTO, @PathVariable(name = "id") long id

    ){
        log.info("Doctor was Updated" + doctorDTO.toString());
        return new ResponseEntity(_DoctorService.updateDoctor(id,doctorDTO), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteDoctor(@PathVariable(name = "id") long id){
        _DoctorService.deleteDoctor(id);
        return ResponseEntity.ok("Deleted Successfully");
    }


}
