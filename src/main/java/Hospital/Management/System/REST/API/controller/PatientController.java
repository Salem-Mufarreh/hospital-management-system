package Hospital.Management.System.REST.API.controller;

import Hospital.Management.System.REST.API.dto.PatientDTO;
import Hospital.Management.System.REST.API.service.PatientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    private PatientService _PatientService;
    private final Logger _log = LoggerFactory.getLogger(PatientController.class);

    public PatientController(PatientService patientService ){
        this._PatientService = patientService;
    }
    @PostMapping("/")
    public ResponseEntity<PatientDTO> CreatePatient(@Valid @RequestBody PatientDTO patientDTO){
        if(patientDTO.getPatientId() == null){
            _log.error("Can't create Patent with empty id");
            return new ResponseEntity(null,HttpStatus.BAD_REQUEST);
        }
        _log.info("Patient Was Created");
        return new ResponseEntity(_PatientService.CreatePatient(patientDTO), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> GetPatientById(
            @PathVariable(name = "id") Long id
    ){
        if(id == null){
            _log.error("id is empty");
            return (ResponseEntity<PatientDTO>) ResponseEntity.badRequest();
        }
        _log.info("Patient id is not null");
        return ResponseEntity.ok(_PatientService.GetPatientById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<PatientDTO>> GetAllPatients(){
        _log.info("Getting Patients");
        return ResponseEntity.ok(_PatientService.GetAllPatients());
    }
    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> UpdatePatient( @PathVariable(name = "id")Long id, @Valid @RequestBody PatientDTO patientDTO){
        if(id != null){
           PatientDTO temp = _PatientService.GetPatientById(id);
           if(temp != null){
               _log.info("Patient Exists");

               return ResponseEntity.ok(_PatientService.UpdatePatient(id, patientDTO));
           }
           else{
               _log.error("Patient Doesn't Exists");
           }
           _log.error("Null id");
        }
        return ResponseEntity.ofNullable(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity DeletePatient(@PathVariable(name = "id")Long id){
        PatientDTO patient = _PatientService.GetPatientById(id);

            _PatientService.DeletePatient(id);
            _log.info("Patient Deleted");
            return ResponseEntity.ok("Patient Deleted");

    }

}
