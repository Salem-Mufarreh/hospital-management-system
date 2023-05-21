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

    @Override
    public AppointmentDTO CreateAppointments(AppointmentDTO appointmentDTO) {
        Appointments appointment = mapToAppointment(appointmentDTO);
        Appointments appointments = _AppointmentsRepository.save(appointment);
        return mapToAppointmentDTO(appointments);
    }

    @Override
    public AppointmentDTO GetAppointmentById(Long id) {
        Appointments appointment = _AppointmentsRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Appointment" ,"id",id));
        return mapToAppointmentDTO(appointment);
    }

    @Override
    public List<AppointmentDTO> GetAllAppointments() {
        List<AppointmentDTO> list = _AppointmentsRepository.findAll().stream().map(d -> mapToAppointmentDTO(d)).toList();
        return list;
    }

    @Override
    public List<AppointmentDTO> GetAppointmentsByDoctor(Long id) {
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

    @Override
    public List<AppointmentDTO> GetAppointmentsForPatient(Long id) {
        PatientDTO patient = _PatientService.GetPatientById(id);
        List<AppointmentDTO> list = _AppointmentsRepository.getAppointmentsByPatientPatientIdOrderByDate(id).stream().map(d-> mapToAppointmentDTO(d)).toList();

        return list;
    }

    @Override
    public AppointmentDTO UpdateAppointment(Long id, AppointmentDTO appointments) {
        return null;
    }

    @Override
    public void DeleteAppointment(Long id) {

    }
    public Appointments mapToAppointment(AppointmentDTO appointmentDTO){
        Appointments appointment = new Appointments();
        appointment.setDate(appointmentDTO.getDate());
        appointment.setStartTime(appointmentDTO.getStartTime());
        appointment.setEndTime(appointmentDTO.getEndTime());
        appointment.setType(appointmentDTO.getType());
        appointment.setStatus(appointmentDTO.getStatus());
        appointment.setCreationDate(appointmentDTO.getCreationDate());
        PatientDTO patientDTO = _PatientService.GetPatientById(appointmentDTO.getAppointmentId());
        appointment.setPatient(_PatientService.toPatient(patientDTO));
        DoctorDTO doctorDTO = _DoctorService.getDoctorById(appointmentDTO.getDoctorId());
        appointment.setDoctor(_DoctorService.mapToEntity(doctorDTO));
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