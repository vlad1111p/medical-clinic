import dao.HibernateUtil;
import model.Appointment;
import model.Doctor;
import model.Patient;
import model.Recipe;
import org.hibernate.Session;
import services.*;
import view.DoctorLogic;
import view.LoginLogic;
import view.MainLogic;
import view.PatientLogic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {


    public static void main(String[] args) {
        System.out.println("sdasd");


        try {
            MainLogic mainLogic = new MainLogic();
            mainLogic.init();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
