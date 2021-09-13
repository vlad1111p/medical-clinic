package view;

import model.Appointment;
import model.Patient;
import model.Recipe;
import services.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class PatientLogic {
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

        MedicalClinicService medicalClinic = new MedicalClinicService();

        Patient patient = new Patient(name, surnName, CNP, age, email, password,
                new HashSet<Appointment>(), medicalClinic.findById(1L));

        PatientService patientService = new PatientService();
        patientService.add(patient);

    }


    public static void makeAppointment(Scanner sc, Patient login) {

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


    public static void viewPatientAppointment(Patient patient) {
        AppointmentService appointmentService = new AppointmentService();
        List<Appointment> appointments = appointmentService.getAll();

        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getId() == patient.getId()) {
                System.out.println(appointment);
            }
        }

    }

    public static List<Appointment> viewPatientAppointmentAsList(Patient patient) {
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

    public static void viewPatientRecipes(Patient patient) {
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

    public static Patient inputLoginPatient(Scanner sc) {
        PatientService patientService = new PatientService();
        List<Patient> patients = patientService.getAll();
        System.out.println("please input username");
//&& patient.getPassword().matches(password)
        String username = sc.nextLine();

        Patient resultPatient = patients.stream().filter(patient -> patient.getEmail().equals(username)).findAny().orElse(null);
        if (resultPatient != null) {
            System.out.println("please insert password");
            String password = sc.nextLine();
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
