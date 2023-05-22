package Hospital.Management.System.REST.API.service;

import Hospital.Management.System.REST.API.dto.MedicalRecordDTO;

import java.util.List;

public interface MedicalRecordService {
    MedicalRecordDTO CreateMedicalRecord(MedicalRecordDTO medicalRecordDTO);
    MedicalRecordDTO GetMedicalRecord(Long id);
    List<MedicalRecordDTO> GetMedicalRecordsAll();
    MedicalRecordDTO UpdateMedicalRecord(Long id, MedicalRecordDTO medicalRecordDTO);
    void DeleteMedicalRecord(Long id);
    List<MedicalRecordDTO> GetMedicalRecordForPatient(Long id);
    List<MedicalRecordDTO> GetMedicalRecordByDoctor(Long id);
    MedicalRecordDTO GetMedicalRecordByAppointment(Long id);

}
