package services;

import dao.HibernateUtil;
import model.Appointment;
import model.Doctor;
import model.MedicalClinic;

import java.util.List;

public class DoctorService {

    HibernateUtil<Doctor> accountGenericDao = new HibernateUtil<>();

    Doctor doctor=new Doctor();

    public void add(Doctor account) {
        accountGenericDao.add(account);
    }

    public List<Doctor> getAll(){
        return accountGenericDao.getAll(doctor);
    }

    public Doctor findById(Long id){
        return accountGenericDao.findById(doctor,id );

    }
}
