package view;

import dao.HibernateUtil;
import model.Doctor;
import model.Patient;
import org.hibernate.Session;
import services.PatientService;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class MainLogic {

    LoginLogic loginLogic = new LoginLogic();
    PatientLogic patientLogic = new PatientLogic();
    DoctorLogic doctorLogic = new DoctorLogic();
    PatientService patientService = new PatientService();

    public MainLogic() {
    }

    public void init() {

//            MedicalClinic medicalClinic = new MedicalClinic("MEDSTAR Policlinica Mănăștur",
//                    "Str. Mehedinţi, nr. 1-3", "0264/917", "email@email.com", new HashSet<Patient>(), new HashSet<Doctor>());
//            MedicalClinicService medicalClinics = new MedicalClinicService();
//            medicalClinics.add(medicalClinic);

        try {
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("=== Welcome to Medical Clinic ===\nInsert an option");

                loginLogic.mainScreen();

                String answer = sc.nextLine();

                if (answer.equals("quit")) {
                    break;
                } else if (answer.equals("rp")) {

                    patientLogic.registerPatient(sc);
                    init();
                    break;

                } else if (answer.equals("lp")) {

                        Patient patient = patientLogic.inputLoginPatient(sc);

                        if (patient == null) {
                            break;
                        }
                        while (true) {
                            patientLogic.loggedPatientOptions(sc);
                            answer = sc.nextLine();

                            if (answer.equals("mp")) {
                                patientLogic.makeAppointment(sc, patient);
                            } else if (answer.equals("vr")) {
                                patientService.viewPatientRecipes(patient);
                            } else if (answer.equals("va")) {
                                patientService.viewPatientAppointmentAsList(patient);
                            } else if (answer.equals("quit")) {
                                break;
                            }
                        }
                } else if (answer.equals("ld")) {
                    while (true) {
                        Doctor doctor = doctorLogic.inputLoginDoctor(sc);
                        if (doctor != null) {
                            while (true) {
                                doctorLogic.showDoctorOptions();
                                answer = sc.nextLine();

                                if (answer.equals("quit")) {
                                    break;
                                }
                                if (answer.equals("va")) {
                                    doctorLogic.viewAppointments(doctor);
                                }
                                if (answer.equals("wr")) {
                                    doctorLogic.writeRecipe(sc, doctor);
                                }


                            }
                        } else if (doctor == null) {
                            break;
                        }
                    }
                } else if (answer.equals("rd")) {
                    doctorLogic.registerDoctor(sc);
                }
            }
        } catch (Exception e) {
            System.err.println("An error has occurred please try again");
            init();
        }
    }
}
