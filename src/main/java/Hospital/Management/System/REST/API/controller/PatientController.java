package Hospital.Management.System.REST.API.controller;

import Hospital.Management.System.REST.API.dto.PatientDTO;
import Hospital.Management.System.REST.API.service.PatientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
