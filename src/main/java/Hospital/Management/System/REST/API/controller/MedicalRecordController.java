package Hospital.Management.System.REST.API.controller;

import Hospital.Management.System.REST.API.dto.MedicalRecordDTO;
import Hospital.Management.System.REST.API.service.MedicalRecordService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/records")
public class MedicalRecordController {
    private final MedicalRecordService _MedicalRecordService;
    private final Logger _log = LoggerFactory.getLogger(MedicalRecordController.class);
    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        _MedicalRecordService = medicalRecordService;
    }
    @PostMapping("/")
    public ResponseEntity<MedicalRecordDTO> CreateMedicalRecord(@Valid @RequestBody MedicalRecordDTO medicalRecordDTO){
        if(medicalRecordDTO.getMedicalRecordId() == null || medicalRecordDTO.getDoctorId() == null
                || medicalRecordDTO.getPatientId() == null || medicalRecordDTO.getAppointmentId() == null) {
            _log.error("Empty id found"+medicalRecordDTO.toString());
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(_MedicalRecordService.CreateMedicalRecord(medicalRecordDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecordDTO> GetRecordById(@PathVariable(name = "id") Long id){
        return new ResponseEntity(_MedicalRecordService.GetMedicalRecord(id),HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<MedicalRecordDTO>> GetAllRecords(){
        return new ResponseEntity(_MedicalRecordService.GetMedicalRecordsAll(),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecordDTO> UpdateMedicalRecord(@PathVariable(name = "id") Long id, @Valid @RequestBody MedicalRecordDTO medicalRecordDTO){
        return new ResponseEntity(_MedicalRecordService.UpdateMedicalRecord(id,medicalRecordDTO),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity DeleteMedicalRecord(@PathVariable(name = "id") long id){
        return ResponseEntity.ok("Medical Record was Deleted!");
    }
    @GetMapping("/patient/{id}")
    public ResponseEntity<List<MedicalRecordDTO>> GetMedicalRecordsForPatient(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(_MedicalRecordService.GetMedicalRecordForPatient(id));
    }
    @GetMapping("/doctor/{id}")
    public ResponseEntity<List<MedicalRecordDTO>> GetMedicalRecordsByDoctor(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(_MedicalRecordService.GetMedicalRecordByDoctor(id));
    }
    @GetMapping("appointment/{id}")
    public ResponseEntity<MedicalRecordDTO> GetMedicalRecordForAppointment(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(_MedicalRecordService.GetMedicalRecordByAppointment(id));
    }
}
