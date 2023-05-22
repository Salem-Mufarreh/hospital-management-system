package Hospital.Management.System.REST.API.controller;

import Hospital.Management.System.REST.API.dto.AppointmentDTO;
import Hospital.Management.System.REST.API.service.AppointmentsService;
import Hospital.Management.System.REST.API.service.DoctorService;
import Hospital.Management.System.REST.API.service.PatientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private AppointmentsService _AppointmentsService;
    private DoctorService _DoctorService;
    private PatientService _PatientService;
    private final Logger _log = LoggerFactory.getLogger(AppointmentController.class);

    public AppointmentController(AppointmentsService appointmentsService, DoctorService doctorService, PatientService patientService) {
        _AppointmentsService = appointmentsService;
        _DoctorService = doctorService;
        _PatientService = patientService;
    }

    @PostMapping("/")
    public ResponseEntity<AppointmentDTO> CreateAppointment(@Valid @RequestBody AppointmentDTO appointments){
        AppointmentDTO appointment = _AppointmentsService.CreateAppointments(appointments);
        return new ResponseEntity(appointment,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> GetAppointmentById(@PathVariable(name = "id")Long id){
        try {
            return new ResponseEntity(_AppointmentsService.GetAppointmentById(id), HttpStatus.OK);
        }catch (Exception ex){
            _log.error(ex.toString());
            return new ResponseEntity(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<AppointmentDTO>> GetAllAppointments(){
        try {
            return new ResponseEntity(_AppointmentsService.GetAllAppointments(), HttpStatus.OK);
        }catch (Exception ex){
            _log.error(ex.toString());
            return new ResponseEntity(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<List<AppointmentDTO>> GetAppointmentsForDoctor(@PathVariable(name = "id")Long id){
        try {
            return new ResponseEntity(_AppointmentsService.GetAppointmentsByDoctor(id),HttpStatus.OK);
        }catch (Exception ex){
            _log.error(ex.toString());
            return new ResponseEntity(null,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/patient/{id}")
    public ResponseEntity<List<AppointmentDTO>> GetAppointmentsByPatient(@PathVariable(name = "id")Long id){
        return new ResponseEntity(_AppointmentsService.GetAppointmentsForPatient(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDTO> UpdateAppointment(@PathVariable(name = "id")Long id, @Valid @RequestBody AppointmentDTO appointmentDTO){
        return new ResponseEntity(_AppointmentsService.UpdateAppointment(id, appointmentDTO),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity DeleteAppointment(@PathVariable(name = "id")Long id){
        _AppointmentsService.DeleteAppointment(id);
        return new ResponseEntity("Appointment Was Deleted",HttpStatus.OK);
    }

}
