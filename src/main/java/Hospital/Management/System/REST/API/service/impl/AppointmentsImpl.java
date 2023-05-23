package Hospital.Management.System.REST.API.service.impl;

import Hospital.Management.System.REST.API.dto.AppointmentDTO;
import Hospital.Management.System.REST.API.dto.DoctorDTO;
import Hospital.Management.System.REST.API.dto.PatientDTO;
import Hospital.Management.System.REST.API.entity.Appointments;
import Hospital.Management.System.REST.API.exception.ResourceNotFoundException;
import Hospital.Management.System.REST.API.repository.AppointmentsRepository;
import Hospital.Management.System.REST.API.service.AppointmentsService;
import Hospital.Management.System.REST.API.service.DoctorService;
import Hospital.Management.System.REST.API.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AppointmentsImpl implements AppointmentsService {

    private final AppointmentsRepository _AppointmentsRepository;
    private DoctorService _DoctorService;
    private PatientService _PatientService;

    public AppointmentsImpl(AppointmentsRepository appointmentsRepository, DoctorService doctorService, PatientService patientService) {
        _AppointmentsRepository = appointmentsRepository;
        _PatientService = patientService;
        _DoctorService = doctorService;
    }
    /* Creates new appointment */
    @Override
    public AppointmentDTO CreateAppointments(AppointmentDTO appointmentDTO) {
        Appointments appointment = mapToAppointment(appointmentDTO);
        appointment.setAppointmentId(new Random().nextLong());
        Appointments appointments = _AppointmentsRepository.save(appointment);
        return mapToAppointmentDTO(appointments);
    }
    /* Get appointment by id if existed*/
    @Override
    public AppointmentDTO GetAppointmentById(Long id) {
        Appointments appointment = _AppointmentsRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Appointment" ,"id",id));
        return mapToAppointmentDTO(appointment);
    }
    /* return a list of all appointments  */
    @Override
    public List<AppointmentDTO> GetAllAppointments() {
        List<AppointmentDTO> list = _AppointmentsRepository.findAll().stream().map(d -> mapToAppointmentDTO(d)).toList();
        return list;
    }
    /* run a query from the Repository to select all appointments for a specific doctor by his ID */
    @Override
    public List<AppointmentDTO> GetAppointmentsByDoctor(Long id) {
        /*Checs if the doctor exists if not will return a run time error with code 404 */
        DoctorDTO doctor = _DoctorService.getDoctorById(id);
        System.out.println(doctor.toString());
        if(doctor != null) {
            List<AppointmentDTO> list = _AppointmentsRepository.getAppointmentsByDoctorId(id).stream().map(d-> mapToAppointmentDTO(d)).toList();
            for (AppointmentDTO appointment : list) {
                System.out.println(appointment);
            }
            return list;
        }
        else{

            return (List<AppointmentDTO>) new ResourceNotFoundException("appointment","id",id);
        }
    }
    /* run a query on the appointments to return list of patient appointments  */
    @Override
    public List<AppointmentDTO> GetAppointmentsForPatient(Long id) {
        /*checks if the patients exists if not it will automatically create a run time error with code 404*/
        PatientDTO patient = _PatientService.GetPatientById(id);
        List<AppointmentDTO> list = _AppointmentsRepository.getAppointmentsByPatientPatientIdOrderByDate(id).stream().map(d-> mapToAppointmentDTO(d)).toList();

        return list;
    }
    /* update an appointment, select previous appointment and check if the appointment exists and update the table  */
    @Override
    public AppointmentDTO UpdateAppointment(Long id, AppointmentDTO appointments) {
        AppointmentDTO appointment = GetAppointmentById(id);
        appointment = appointments;
        _AppointmentsRepository.save(mapToAppointment(appointment));
        return appointment;
    }
    /*delete the appointment */
    @Override
    public void DeleteAppointment(Long id) {
        AppointmentDTO appointmentDTO = GetAppointmentById(id);
        _AppointmentsRepository.delete(mapToAppointment(appointmentDTO));
    }
    /*map from DTO to entity */
    public Appointments mapToAppointment(AppointmentDTO appointmentDTO){
        Appointments appointment = new Appointments();
        appointment.setType(appointmentDTO.getType());
        appointment.setStatus(appointmentDTO.getStatus());
        appointment.setStartTime(appointmentDTO.getStartTime());
        appointment.setEndTime(appointmentDTO.getEndTime());
        appointment.setCreationDate(appointmentDTO.getCreationDate());
        appointment.setDate(appointmentDTO.getDate());
        /*from doctor id selects the doctor and retrieve it and if the doctor doesn't exist it will return a 404 error*/
        appointment.setDoctor(_DoctorService.mapToEntity(_DoctorService.getDoctorById(appointmentDTO.getDoctorId())));
        /* transform from patient id to patient and checks if the patient exist if not return a 404 error*/
        appointment.setPatient(_PatientService.toPatient(_PatientService.GetPatientById(appointmentDTO.getPatientId())));
        appointment.setAppointmentId(appointmentDTO.getAppointmentId());
        return appointment;
    }
    public AppointmentDTO mapToAppointmentDTO(Appointments appointment){
        AppointmentDTO savedAppointmentDTO = new AppointmentDTO();
        savedAppointmentDTO.setAppointmentId(appointment.getAppointmentId());
        savedAppointmentDTO.setDate(appointment.getDate());
        savedAppointmentDTO.setStartTime(appointment.getStartTime());
        savedAppointmentDTO.setEndTime(appointment.getEndTime());
        savedAppointmentDTO.setType(appointment.getType());
        savedAppointmentDTO.setStatus(appointment.getStatus());
        savedAppointmentDTO.setCreationDate(appointment.getCreationDate());
        savedAppointmentDTO.setDoctorId(appointment.getDoctor().getDoctorId());
        savedAppointmentDTO.setPatientId(appointment.getPatient().getPatientId());
        return savedAppointmentDTO;
    }

}
