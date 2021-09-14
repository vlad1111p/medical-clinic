package services;

import dao.GenericDao;
import model.Patient;

import java.util.List;

public class PatientService {

    GenericDao<Patient> patientGenericDao = new GenericDao<>();
    Patient patient = new Patient();

    public void add(Patient patient) {
        patientGenericDao.add(patient);
    }

    public List<Patient> getAll() {
        return patientGenericDao.getAll(patient);
    }
}
