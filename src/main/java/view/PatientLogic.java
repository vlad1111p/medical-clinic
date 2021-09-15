package view;

import model.Appointment;
import model.Patient;
import services.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class PatientLogic {


    DoctorService doctorService = new DoctorService();
    AppointmentService appointmentService = new AppointmentService();
    PatientService patientService = new PatientService();
    MedicalClinicService medicalClinic = new MedicalClinicService();
    RecipeService recipeService = new RecipeService();
    DoctorLogic doctorLogic = new DoctorLogic();

    public PatientLogic() {
    }

    public void registerPatient(Scanner sc) {

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

    public void makeAppointment(Scanner sc, Patient login) {

        System.out.println("please choose the id for which doctor");
        doctorService.showAllDoctor();
        Long idChoice = sc.nextLong();
        System.out.println("please insert a date with format yyyy-MM-dd HH");
        sc.skip("\n");
        String date = sc.nextLine().concat(":00:00");
        System.out.println("string inserted date: "+date);
        try {
            Timestamp localDateTime = java.sql.Timestamp.valueOf(date);
            System.out.println("local date time (timestamp)"+localDateTime);

            if (localDateTime.after(Timestamp.valueOf(LocalDateTime.now()))) {
                System.out.println("local date time (timestamp)"+localDateTime);

                Appointment appointment = new Appointment(login,
                        doctorService.findById(idChoice)
                        , localDateTime);

                if (appointmentService.isAppointmentTaken(localDateTime, idChoice)) {
                    System.out.println("appointment time is already taken,\n " +
                            "try again with a new hour");
                    makeAppointment(sc, login);
                } else {
                    appointmentService.add(appointment);
                    System.out.println(appointment);
                    System.out.println("appointment was successfully created, at time "+localDateTime);
                }
            } else {
                System.out.println("please insert a date after " + Timestamp.valueOf(LocalDateTime.now()) + " format yyyy-MM-dd HH:mm:ss");
                makeAppointment(sc, login);

            }
        } catch (Exception e) {
            System.err.println("wrong format for date");
            e.printStackTrace();
            makeAppointment(sc, login);
        }
    }


    public Patient inputLoginPatient(Scanner sc) {

        List<Patient> patients = patientService.getAll();

        System.out.println("please insert email");

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
            System.out.println("wrong email");
            return inputLoginPatient(sc);
        }
    }

    public void loggedPatientOptions(Scanner sc) {
        System.out.println("make an appointment: mp");
        System.out.println("view your appointments: va");
        System.out.println("view your recipes: vr");
        System.out.println("quit");
    }
}
