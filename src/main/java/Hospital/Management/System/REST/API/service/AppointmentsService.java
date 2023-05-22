package Hospital.Management.System.REST.API.service;

import Hospital.Management.System.REST.API.dto.AppointmentDTO;
import Hospital.Management.System.REST.API.entity.Appointments;

import java.util.List;

public interface AppointmentsService {
    AppointmentDTO CreateAppointments(AppointmentDTO appointments);
    AppointmentDTO GetAppointmentById(Long id);
    List<AppointmentDTO> GetAllAppointments();
    List<AppointmentDTO> GetAppointmentsByDoctor(Long id);
    List<AppointmentDTO> GetAppointmentsForPatient(Long id);
    AppointmentDTO UpdateAppointment(Long id, AppointmentDTO appointments);
    void DeleteAppointment(Long id);
    Appointments mapToAppointment(AppointmentDTO appointmentDTO);
    AppointmentDTO mapToAppointmentDTO(Appointments appointment);
}
