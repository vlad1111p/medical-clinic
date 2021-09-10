package services;

import dao.HibernateUtil;
import model.Patient;

import java.util.List;

public class PatientService {

    HibernateUtil<Patient> accountGenericDao = new HibernateUtil<>();

    Patient patient = new Patient();

    public void add(Patient account) {
        accountGenericDao.add(account);
    }

    public List<Patient> getAll(){
     return accountGenericDao.getAll(patient);
    }

}
