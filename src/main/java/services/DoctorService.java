package services;

import dao.GenericDao;
import model.Doctor;

import java.util.List;

public class DoctorService {

    GenericDao<Doctor> doctorGenericDao = new GenericDao<>();
    Doctor doctor = new Doctor();


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
}
