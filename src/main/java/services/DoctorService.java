package services;

import dao.GenericDao;
import model.Appointment;
import model.Doctor;

import java.util.List;
import java.util.stream.Collectors;

public class DoctorService {

    GenericDao<Doctor> doctorGenericDao = new GenericDao<>();
    Doctor doctor = new Doctor();
    AppointmentService appointmentService = new AppointmentService();


    public void add(Doctor doctor) {
        doctorGenericDao.add(doctor);
    }

    public List<Doctor> getAll() {
        return doctorGenericDao.getAll(doctor);
    }

    public Doctor findById(Long id) {
        return doctorGenericDao.findById(doctor, id);
    }

    public void showAllDoctor() {
        List<Doctor> listOfDoctor = getAll();
        listOfDoctor.forEach(doctor -> System.out.println(doctor.toString()));
    }

    public List<Appointment> viewDoctorAppointments(Doctor doctor) {
        List<Appointment> appointments = appointmentService.getAll().stream()
                .filter(appointment -> appointment.getDoctor().getId() == doctor.getId())
                .collect(Collectors.toList());

        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
        return appointments;
    }
}
