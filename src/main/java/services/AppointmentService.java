package services;

import dao.HibernateUtil;
import model.Appointment;

import java.util.List;

public class AppointmentService {


    HibernateUtil<Appointment> accountGenericDao = new HibernateUtil<>();

    Appointment appointment = new Appointment();

    public void add(Appointment account) {
        accountGenericDao.add(account);
    }

    public List<Appointment> getAll() {
        return accountGenericDao.getAll(appointment);
    }
}


