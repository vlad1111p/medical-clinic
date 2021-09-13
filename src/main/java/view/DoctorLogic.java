package view;

import model.Appointment;
import model.Doctor;
import model.Specialization;
import services.DoctorService;
import services.MedicalClinicService;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class DoctorLogic {

    private static final DoctorService doctorService = new DoctorService();

    public DoctorLogic() {
    }

    public static void registerDoctor(Scanner sc) {

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


        MedicalClinicService medicalClinic = new MedicalClinicService();

        Doctor doctor = new Doctor(name, surnName, email, password,
                new HashSet<Appointment>(), Specialization.valueOf(specialisation.toUpperCase()), medicalClinic.findById(1L));


        doctorService.add(doctor);

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

    public void listOfAppointments() {
        DoctorService doctorService = new DoctorService();


    }

    public void showAllDoctor() {
        DoctorService ds = new DoctorService();

        List<Doctor> listOfDoctor = ds.getAll();

        listOfDoctor.stream().forEach(doctor -> System.out.println(doctor.toString()));
    }


}
