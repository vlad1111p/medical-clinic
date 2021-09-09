package services;

import dao.HibernateUtil;
import model.MedicalClinic;
import model.Patient;

public class PatientService {

    HibernateUtil<Patient> accountGenericDao = new HibernateUtil<>();

    public void addAccount(Patient account) {
        accountGenericDao.add(account);
    }
}
