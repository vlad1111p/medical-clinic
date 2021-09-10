import dao.HibernateUtil;
import model.Appointment;
import model.Doctor;
import model.Patient;
import model.Recipe;
import org.hibernate.Session;
import services.*;

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

                mainScreen();

                String answer = sc.nextLine();

                if (answer.equals("quit")) {

                    break;
                } else if (answer.equals("rp")) {

                    registerPatient(sc);

                } else if (answer.equals("lp")) {
                    while (true) {

                        Patient login = inputLoginPatient(sc);

                        while (login != null) {
                            loggedPatientOptions(sc);
                            answer = sc.nextLine();


                            if (answer.equals("mp")) {

                                makeAppointment(sc, login);


                            } else if (answer.equals("vr")) {


                                viewPatientRecipes(login);


                            } else if (answer.equals("va")) {

                                viewPatientAppointment(login);


                            } else if (answer.equals("quit")) {
                                break;
                            }


                        }

                    }


                } else if (answer.equals("ld")) {
                    while (true) {
                        boolean login = inputLoginDoctor(sc);
                        if (login) {
                            System.out.println("youtube");

                        }

                    }

                }


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void makeAppointment(Scanner sc, Patient login) {

        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        System.out.println(randomDate);

        AppointmentService appointmentService = new AppointmentService();
        DoctorService doctorService = new DoctorService();
        Appointment appointment = new Appointment(login,
                doctorService.findById(ThreadLocalRandom.current().nextLong(1L, Long.valueOf(doctorService.getAll().size())))
                , randomDate);
        appointmentService.add(appointment);
    }


    private static void viewPatientAppointment(Patient patient) {
        AppointmentService appointmentService = new AppointmentService();
        List<Appointment> appointments = appointmentService.getAll();

        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getId() == patient.getId()) {
                System.out.println(appointment);
            }
        }

    }

    private static List<Appointment> viewPatientAppointmentAsList(Patient patient) {
        AppointmentService appointmentService = new AppointmentService();
        List<Appointment> appointments = appointmentService.getAll();
        List<Appointment> returneableAppointments = new ArrayList<Appointment>();

        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getId() == patient.getId()) {
                returneableAppointments.add(appointment);
            }
        }
        return returneableAppointments;
    }

    private static void viewPatientRecipes(Patient patient) {
        RecipeService recipeService = new RecipeService();
        List<Recipe> recipes = recipeService.getAll();
        List<Appointment> appointments = viewPatientAppointmentAsList(patient);
        for (Recipe recipe : recipes) {
            for (Appointment appointment : appointments) {
                if (recipe.getAppointment().getId() == appointment.getId()) {
                    System.out.println(recipe);
                }
            }
        }

    }

    public static void mainScreen() {

        System.out.println("Login as patient :lp");
        System.out.println("Login as doctor :ld");
        System.out.println("Register as patient: rp");
        System.out.println("quit");


    }


    public static void loginPatient(Scanner sc) {
        System.out.println();


    }

    public static void registerPatient(Scanner sc) {

        System.out.println("please fill in your name");

        String name = sc.nextLine();

        System.out.println("please fill in your surname");

        String surnName = sc.nextLine();

        System.out.println("please fill in your CNP");

        String CNP = sc.nextLine();

        System.out.println("please fill in your email/username");

        String email = sc.nextLine();

        System.out.println("please fill in your password");

        String password = sc.nextLine();

        System.out.println("please fill in your age");
        int age = sc.nextInt();

        MedicalClinicService medicalClinic = new MedicalClinicService();

        Patient patient = new Patient(name, surnName, CNP, age, email, password,
                new HashSet<Appointment>(), medicalClinic.findById(1L));

        PatientService patientService = new PatientService();
        patientService.add(patient);

    }

    public static Patient inputLoginPatient(Scanner sc) {

        System.out.println("please input username");

        String username = sc.nextLine();

        System.out.println("please input password");

        String password = sc.nextLine();


        PatientService patientService = new PatientService();

        List<Patient> patients = patientService.getAll();

//        for (Patient patient : patients) {
//            System.out.println(patient);
//        }

        boolean contains = false;
        for (Patient patient : patients) {
            if (patient.getEmail().matches(username) && patient.getPassword().matches(password)) {
                return patient;
            }

        }

        return null;

    }

    public static boolean inputLoginDoctor(Scanner sc) {
        System.out.println("please input username");
        String username = sc.nextLine();

        System.out.println("please input password");
        String password = sc.nextLine();


        DoctorService doctorService = new DoctorService();
        List<Doctor> doctors = doctorService.getAll();

//        for (Patient patient : patients) {
//            System.out.println(patient);
//        }

        boolean contains = false;
        for (Doctor doctor : doctors) {
            return doctor.getEmail().matches(username) && doctor.getPassword().matches(password);

        }

        return false;

    }

    public static void loggedPatientOptions(Scanner sc) {
        System.out.println("make appointment: mp");
        System.out.println("view your appointments: va");
        System.out.println("view your recipes: vr");
        System.out.println("quit");


    }


}
