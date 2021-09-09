package services;

import dao.HibernateUtil;
import model.Appointment;

public class AppointmentService {


    HibernateUtil<Appointment> accountGenericDao = new HibernateUtil<>();

    public void addAccount(Appointment account)
    {
        accountGenericDao.add(account);
    }
}


