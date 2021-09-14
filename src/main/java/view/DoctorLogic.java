package view;

import model.Appointment;
import model.Doctor;
import model.Patient;
import model.Specialization;
import services.DoctorService;
import services.MedicalClinicService;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class DoctorLogic {

    private static final DoctorService doctorService = new DoctorService();
    private static final MedicalClinicService medicalClinic = new MedicalClinicService();

    public DoctorLogic() {
    }

    public void registerDoctor(Scanner sc) {

        System.out.println("please fill in your name");

        String name = sc.nextLine();

        System.out.println("please fill in your surname");

        String surnName = sc.nextLine();

        System.out.println("what is your specialisation");
        for (Specialization specialisation : Specialization.values()) {

            System.out.println(specialisation);
        }
        String specialisation = sc.nextLine();

        System.out.println("please fill in your email");

        String email = sc.nextLine();

        System.out.println("please fill in your password");

        String password = sc.nextLine();

        Doctor doctor = new Doctor(name, surnName, email, password,
                new HashSet<Appointment>(), Specialization.valueOf(specialisation.toUpperCase()), medicalClinic.findById(1L));

        doctorService.add(doctor);
    }

    public Doctor inputLoginDoctor(Scanner sc) {
        List<Doctor> doctors = doctorService.getAll();

        System.out.println("please insert email");

        String username = sc.nextLine();

        if (username.equals("quit")) {
            return null;
        }
        Doctor resultDoctor = doctors.stream().filter(doctor -> doctor.getEmail().equals(username)).findAny().orElse(null);
        if (resultDoctor != null) {
            System.out.println("please insert password");
            String password = sc.nextLine();

            if (password.equals("quit")) {
                return null;
            }
            if (resultDoctor.getPassword().equals(password)) {
                return resultDoctor;
            } else {
                System.out.println("wrong password");
                return inputLoginDoctor(sc);
            }
        } else {
            System.out.println("wrong email");
            return inputLoginDoctor(sc);
        }
    }

    public void listOfAppointments() {}

}
