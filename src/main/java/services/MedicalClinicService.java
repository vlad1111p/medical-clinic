package services;

import dao.HibernateUtil;
import model.MedicalClinic;

public class MedicalClinicService {

    HibernateUtil<MedicalClinic> accountGenericDao = new HibernateUtil<>();
    MedicalClinic medicalClinic = new MedicalClinic();

    public void add(MedicalClinic account) {
        accountGenericDao.add(account);
    }

    public MedicalClinic findById( Long id){
       return accountGenericDao.findById(medicalClinic,id );

    }

}
