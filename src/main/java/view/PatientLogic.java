package view;

import model.Appointment;
import model.Patient;
import model.Recipe;
import services.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class PatientLogic {


    static DoctorService doctorService = new DoctorService();

    static AppointmentService appointmentService = new AppointmentService();

    static PatientService patientService = new PatientService();

    static MedicalClinicService medicalClinic = new MedicalClinicService();

    static RecipeService recipeService = new RecipeService();

    public PatientLogic() {
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


        Patient patient = new Patient(name, surnName, CNP, age, email, password,
                new HashSet<Appointment>(), medicalClinic.findById(1L));


        patientService.add(patient);

    }


    public static void makeAppointment(Scanner sc, Patient login) {
        DoctorLogic doctorLogic = new DoctorLogic();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        System.out.println("please choose the id for which doctor");
        doctorLogic.showAllDoctor();
        Long idChoice = sc.nextLong();
        System.out.println("please insert a date with format dd/mm/yyyy");
        String date = sc.next();
        try {
            LocalDate localDate = LocalDate.parse(date, formatter);
            if (localDate.isAfter(LocalDate.now())) {

                Appointment appointment = new Appointment(login,
                        doctorService.findById(idChoice)
                        , localDate);
                appointmentService.add(appointment);
            }else{
                System.out.println("please insert a date after "+ LocalDate.now().format(formatter) + " format dd/mm/yyyy");
                date = sc.next();
            }
        } catch (Exception e) {
            System.err.println("wrong format for date");
            makeAppointment(sc, login);
        }
    }


    public static void viewPatientAppointment(Patient patient) {

        List<Appointment> appointments = appointmentService.getAll();

        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getId() == patient.getId()) {
                System.out.println(appointment);
            }
        }

    }

    public static List<Appointment> viewPatientAppointmentAsList(Patient patient) {

        List<Appointment> appointments = appointmentService.getAll();
        List<Appointment> returneableAppointments = new ArrayList<Appointment>();

        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getId() == patient.getId()) {
                returneableAppointments.add(appointment);
            }
        }
        return returneableAppointments;
    }

    public static void viewPatientRecipes(Patient patient) {

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

    public static Patient inputLoginPatient(Scanner sc) {

        List<Patient> patients = patientService.getAll();
        System.out.println("please input username");
//&& patient.getPassword().matches(password)
        String username = sc.nextLine();

        if (username.equals("quit")) {
            return null;
        }

        Patient resultPatient = patients.stream().filter(patient -> patient.getEmail().equals(username)).findAny().orElse(null);
        if (resultPatient != null) {
            System.out.println("please insert password");
            String password = sc.nextLine();

            if (password.equals("quit")) {
                return null;
            }
            if (resultPatient.getPassword().equals(password)) {
                return resultPatient;
            } else {
                System.out.println("wrong password");
                return inputLoginPatient(sc);
            }

        } else {
            System.out.println("wrong username");
            return inputLoginPatient(sc);

        }

    }


    public static void loggedPatientOptions(Scanner sc) {
        System.out.println("make appointment: mp");
        System.out.println("view your appointments: va");
        System.out.println("view your recipes: vr");
        System.out.println("quit");


    }

}
