package Hospital.Management.System.REST.API.service.impl;

import Hospital.Management.System.REST.API.dto.AppointmentDTO;
import Hospital.Management.System.REST.API.dto.DoctorDTO;
import Hospital.Management.System.REST.API.dto.MedicalRecordDTO;
import Hospital.Management.System.REST.API.dto.PatientDTO;
import Hospital.Management.System.REST.API.entity.MedicalRecord;
import Hospital.Management.System.REST.API.exception.RecordExistsException;
import Hospital.Management.System.REST.API.exception.ResourceNotFoundException;
import Hospital.Management.System.REST.API.repository.MedicalRecordRepository;
import Hospital.Management.System.REST.API.service.AppointmentsService;
import Hospital.Management.System.REST.API.service.DoctorService;
import Hospital.Management.System.REST.API.service.MedicalRecordService;
import Hospital.Management.System.REST.API.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class MedicalRecordImpl implements MedicalRecordService {
    private final MedicalRecordRepository _MedicalRecordRepository;
    private final DoctorService _DoctorService;
    private final PatientService _PatientService;
    private final AppointmentsService _AppointmentsService;

    public MedicalRecordImpl(MedicalRecordRepository medicalRecordRepository, DoctorService doctorService, PatientService patientService, AppointmentsService appointmentsService) {
        _MedicalRecordRepository = medicalRecordRepository;
        _DoctorService = doctorService;
        _PatientService = patientService;
        _AppointmentsService = appointmentsService;
    }

    @Override
    public MedicalRecordDTO CreateMedicalRecord(MedicalRecordDTO medicalRecordDTO) {
        /*checks to see if any of (doctor, patient,appointment) doesn't exist to return an error*/
        DoctorDTO doctorDTO = _DoctorService.getDoctorById(medicalRecordDTO.getDoctorId());
        PatientDTO patientDTO = _PatientService.GetPatientById(medicalRecordDTO.getPatientId());
        AppointmentDTO appointmentDTO = _AppointmentsService.GetAppointmentById(medicalRecordDTO.getAppointmentId());
        MedicalRecord record = _MedicalRecordRepository.getMedicalRecordByAppointment(appointmentDTO.getAppointmentId());
        if(record != null){
            /*if the record already exists because of 1-1 relationship you can't create a new one but can update it
            * return a runtime error for conflict
            * */
            throw new RecordExistsException("Medical record already exists for the appointment");
        }
        MedicalRecord medicalRecord = mapToEntity(medicalRecordDTO);
        medicalRecord.setMedicalRecordId(new Random().nextLong());
        System.out.println(medicalRecord.toString());
        MedicalRecord NewmedicalRecord = _MedicalRecordRepository.save(medicalRecord);
        return mapToDTO(NewmedicalRecord);
    }

    @Override
    public MedicalRecordDTO GetMedicalRecord(Long id) {
        MedicalRecord medicalRecord = _MedicalRecordRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Records", "id", id));

        return mapToDTO(medicalRecord);
    }

    @Override
    public List<MedicalRecordDTO> GetMedicalRecordsAll() {
        List<MedicalRecordDTO> list = _MedicalRecordRepository.findAll().stream().map(a -> mapToDTO(a)).toList();
        return list;
    }

    @Override
    public MedicalRecordDTO UpdateMedicalRecord(Long id, MedicalRecordDTO medicalRecordDTO) {
        MedicalRecord medicalRecord = mapToEntity(GetMedicalRecord(id));
        medicalRecord = mapToEntity(medicalRecordDTO);
        MedicalRecordDTO newMed = mapToDTO(_MedicalRecordRepository.save(medicalRecord));
        return newMed;
    }

    @Override
    public void DeleteMedicalRecord(Long id) {
        MedicalRecord medicalRecord = mapToEntity(GetMedicalRecord(id));
        _MedicalRecordRepository.delete(medicalRecord);
    }
    /*run a query to get all the records for the patient */
    @Override
    public List<MedicalRecordDTO> GetMedicalRecordForPatient(Long id) {
        PatientDTO patientDTO = _PatientService.GetPatientById(id);

        List<MedicalRecordDTO> list = _MedicalRecordRepository.getMedicalRecordByPatientId(id).stream().map(a -> mapToDTO(a)).toList();
        return list;
    }
    /*get all records that have been added by doctor */
    @Override
    public List<MedicalRecordDTO> GetMedicalRecordByDoctor(Long id) {
        DoctorDTO doctorDTO = _DoctorService.getDoctorById(id);
        List<MedicalRecordDTO> list = _MedicalRecordRepository.getMedicalRecordByDoctorId(id).stream().map(a -> mapToDTO(a)).toList();
        return list;
    }
    /*get medical record by using the appointment */
    @Override
    public MedicalRecordDTO GetMedicalRecordByAppointment(Long id) {
        AppointmentDTO appointmentDTO = _AppointmentsService.GetAppointmentById(id);
        MedicalRecord record = _MedicalRecordRepository.getMedicalRecordByAppointment(id);
        if(record == null){
            throw new ResourceNotFoundException("MedicalRecord","id",id);
        }
        return mapToDTO(record);
    }

    public MedicalRecordDTO mapToDTO(MedicalRecord medicalRecord){
        MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
        medicalRecordDTO.setMedicalRecordId(medicalRecord.getMedicalRecordId());
        medicalRecordDTO.setDiagnosis(medicalRecord.getDiagnosis());
        medicalRecordDTO.setTreatment(medicalRecord.getTreatment());
        medicalRecordDTO.setLastModifiedDate(medicalRecord.getLastModifiedDate());
        medicalRecordDTO.setAppointmentId(medicalRecord.getAppointment().getAppointmentId());
        medicalRecordDTO.setPatientId(medicalRecord.getPatient().getPatientId());
        medicalRecordDTO.setDoctorId(medicalRecord.getDoctor().getDoctorId());
        return medicalRecordDTO;
    }
    public MedicalRecord mapToEntity(MedicalRecordDTO medicalRecordDTO){
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setMedicalRecordId(medicalRecordDTO.getMedicalRecordId());
        medicalRecord.setDiagnosis(medicalRecordDTO.getDiagnosis());
        medicalRecord.setTreatment(medicalRecordDTO.getTreatment());
        medicalRecord.setLastModifiedDate(medicalRecordDTO.getLastModifiedDate());
        medicalRecord.setDoctor(_DoctorService.mapToEntity(_DoctorService.getDoctorById(medicalRecordDTO.getDoctorId())));
        medicalRecord.setPatient(_PatientService.toPatient(_PatientService.GetPatientById(medicalRecordDTO.getPatientId())));
        medicalRecord.setAppointment(_AppointmentsService.mapToAppointment(_AppointmentsService.GetAppointmentById(medicalRecordDTO.getAppointmentId())));
        return medicalRecord;
    }
}
