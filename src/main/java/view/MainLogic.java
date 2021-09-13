package view;

import dao.HibernateUtil;
import model.Patient;
import org.hibernate.Session;
import services.RecipeService;

import java.util.Scanner;

public class MainLogic {
    public MainLogic() {
    }


    public void init(){
    DoctorLogic doctorLogic= new DoctorLogic();
    LoginLogic loginLogic = new LoginLogic();
    PatientLogic patientLogic = new PatientLogic();
    Session session = HibernateUtil.getSessionFactory().openSession();
//            MedicalClinic medicalClinic = new MedicalClinic("MEDSTAR Policlinica Mănăștur",
//                    "Str. Mehedinţi, nr. 1-3", "0264/917", "email@email.com", new HashSet<Patient>(), new HashSet<Doctor>());
//            MedicalClinicService medicalClinics = new MedicalClinicService();
//            medicalClinics.add(medicalClinic);
//            Doctor doctor = new Doctor("goldman", "felton", "vlad", "vlad",
//                    new HashSet<Appointment>(), Specialization.DERMATOLOGIST, medicalClinics.findById(1L));
//            DoctorService doctorService = new DoctorService();
//            doctorService.add(doctor);


    RecipeService recipe = new RecipeService();

    Scanner sc = new Scanner(System.in);

            while (true) {
        System.out.println("Insert option");

        loginLogic.mainScreen();

        String answer = sc.nextLine();

        if (answer.equals("quit")) {

            break;
        } else if (answer.equals("rp")) {

            patientLogic.registerPatient(sc);

        } else if (answer.equals("lp")) {
            while (true) {

                Patient login = patientLogic.inputLoginPatient(sc);

                while (login != null) {
                    patientLogic.loggedPatientOptions(sc);
                    answer = sc.nextLine();


                    if (answer.equals("mp")) {

                        patientLogic.makeAppointment(sc, login);


                    } else if (answer.equals("vr")) {


                        patientLogic.viewPatientRecipes(login);


                    } else if (answer.equals("va")) {

                        patientLogic.viewPatientAppointment(login);


                    } else if (answer.equals("quit")) {
                        break;
                    }


                }

            }


        } else if (answer.equals("ld")) {
            while (true) {
                boolean login = doctorLogic.inputLoginDoctor(sc);
                if (login) {
                    System.out.println("youtube");

                }

            }

        }


    }}
}
