package view;

import model.*;
import services.AppointmentService;
import services.DoctorService;
import services.MedicalClinicService;
import services.RecipeService;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DoctorLogic {

    private static final DoctorService doctorService = new DoctorService();
    private static final MedicalClinicService medicalClinic = new MedicalClinicService();
    private static final RecipeService recipeService = new RecipeService();
    private static final AppointmentService appointmentService = new AppointmentService();

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

    public void showDoctorOptions() {

        System.out.println("view appointments: va");
        System.out.println("write recipe: wr");
        System.out.println("quit");
    }

    public void writeRecipe(Scanner sc, Doctor doctor) {

        System.out.println("For which appointment do you want to write a recipe?");
        System.out.println("Choose by id");
        doctorService.viewDoctorAppointments(doctor);

        Long choice = Long.valueOf(sc.nextLine());

        Appointment appointmentToChangeRecipe = appointmentService.findById(choice);

        Recipe recipeOfAppointment = recipeService.getAll().stream()
                .filter(recipe -> recipe.getAppointment().getId() == appointmentToChangeRecipe.getId())
                .findFirst().orElse(null);

        if (recipeOfAppointment == null) {
            Recipe recipe = new Recipe();
            System.out.println("write the investigation description");
            recipe.setInvestigation_description(sc.nextLine());
            System.out.println("write the disease");
            recipe.setDiseases(sc.nextLine());
            System.out.println("write the prescribed medication");
            recipe.setPrescribed_medication(sc.nextLine());

            recipe.setAppointment(appointmentToChangeRecipe);

            recipeService.addRecipe(recipe);

        } else {
            System.out.println("the investigation description is " + recipeOfAppointment.getInvestigation_description());
            System.out.println("is it correct y/n");

            String answer = sc.nextLine();

            if (answer.equals("n")) {
                System.out.println("wirte the new investigation description");
                recipeOfAppointment.setInvestigation_description(sc.nextLine());
            }
            System.out.println("the disease is " + recipeOfAppointment.getDiseases());
            System.out.println("is it correct y/n");
            answer = sc.nextLine();
            if (answer.equals("n")) {
                System.out.println("write the disease description");
                recipeOfAppointment.setDiseases(sc.nextLine());
            }
            System.out.println("the prescribed medication is " + recipeOfAppointment.getPrescribed_medication());
            System.out.println("is it correct y/n");
            answer = sc.nextLine();
            if (answer.equals("n")) {
                System.out.println("write the prescribed medication ");
                recipeOfAppointment.setPrescribed_medication(sc.nextLine());
            }

            recipeService.updateRecipe(recipeOfAppointment);
        }
    }
}
