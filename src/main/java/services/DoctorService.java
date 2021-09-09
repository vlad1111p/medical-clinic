package services;

import dao.HibernateUtil;
import model.Appointment;
import model.Doctor;

public class DoctorService {

    HibernateUtil<Doctor> accountGenericDao = new HibernateUtil<>();

    public void addAccount(Doctor account)
    {
        accountGenericDao.add(account);
    }
}
