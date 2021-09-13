package view;

import model.Doctor;
import services.DoctorService;

import java.util.List;
import java.util.Scanner;

public class DoctorLogic {


    public DoctorLogic() {
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

    public void showAllDoctor(){
        DoctorService ds=new DoctorService();

        List<Doctor> listOfDoctor = ds.getAll();

        listOfDoctor.stream().forEach( doctor-> System.out.println(doctor.toString()));
    }


}
