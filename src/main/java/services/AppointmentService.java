package services;

import dao.GenericDao;
import model.Appointment;
import model.Doctor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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

    public boolean isAppointmentTaken(Timestamp givenTime, Long doctorId) {
        DoctorService doctorService = new DoctorService();
        Doctor doctor = doctorService.findById(doctorId);
        List<Appointment> appointmentList = doctorService.viewDoctorAppointments(doctor);
        Appointment appointment = appointmentList.stream().filter(appointment1 -> appointment1.getDateAndTime()
                        .equals(givenTime))
                .findAny()
                .orElse(null);
        if (appointment == null) {
            return false;
        }
        return true;
    }

}


