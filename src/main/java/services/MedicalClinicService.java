package services;

import dao.HibernateUtil;
import model.Doctor;
import model.MedicalClinic;

public class MedicalClinicService {

    HibernateUtil<MedicalClinic> accountGenericDao = new HibernateUtil<>();

    public void addAccount(MedicalClinic account)
    {
        accountGenericDao.add(account);
    }
}
