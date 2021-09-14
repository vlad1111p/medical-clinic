package services;

import dao.GenericDao;
import model.Appointment;
import model.Doctor;
import model.Patient;

import java.util.ArrayList;
import java.util.List;

public class AppointmentService {

    GenericDao<Appointment> appointmentGenericDao = new GenericDao<>();
    Appointment appointment = new Appointment();

    public void add(Appointment appointment) {
        appointmentGenericDao.add(appointment);
    }

    public List<Appointment> getAll() {
        return appointmentGenericDao.getAll(appointment);
    }
    public Appointment findById(Long id) {
        return appointmentGenericDao.findById(appointment, id);
    }

}


