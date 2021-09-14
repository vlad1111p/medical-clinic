package services;

import dao.GenericDao;
import model.MedicalClinic;

public class MedicalClinicService {

    GenericDao<MedicalClinic> medicalClinicGenericDao = new GenericDao<>();
    MedicalClinic medicalClinic = new MedicalClinic();

    public void add(MedicalClinic medicalClinic) {
        medicalClinicGenericDao.add(medicalClinic);
    }

    public MedicalClinic findById(Long id) {
        return medicalClinicGenericDao.findById(medicalClinic, id);
    }
}
