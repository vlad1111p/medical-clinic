package view;

import model.Doctor;
import model.Patient;
import services.DoctorService;
import services.PatientService;

import java.util.Scanner;

public class MainLogic {

    LoginLogic loginLogic = new LoginLogic();
    PatientLogic patientLogic = new PatientLogic();
    DoctorLogic doctorLogic = new DoctorLogic();
    PatientService patientService = new PatientService();
    DoctorService doctorService = new DoctorService();

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
                    sc.skip("\n");

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
                            sc.skip("\n");
                        } else if (answer.equals("vr")) {
                            patientService.viewPatientRecipes(patient);
                        } else if (answer.equals("va")) {
                            patientService.viewPatientAppointment(patient);
                        } else if (answer.equals("quit")) {
                            break;
                        }
                    }
                } else if (answer.equals("ld")) {
                        Doctor doctor = doctorLogic.inputLoginDoctor(sc);
                        if (doctor != null) {
                            while (true) {
                                doctorLogic.showDoctorOptions();
                                answer = sc.nextLine();
                                if (answer.equals("quit")) {
                                    break;
                                }
                                if (answer.equals("va")) {
                                    doctorService.viewDoctorAppointments(doctor);
                                }
                                if (answer.equals("wr")) {
                                    doctorLogic.writeRecipe(sc, doctor);
                                }
                            }
                        } else {
                            break;
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
