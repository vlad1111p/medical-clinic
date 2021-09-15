package services;

import dao.GenericDao;
import model.Appointment;
import model.Patient;
import model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class PatientService {

    GenericDao<Patient> patientGenericDao = new GenericDao<>();
    Patient patient = new Patient();
    AppointmentService appointmentService = new AppointmentService();

    public void add(Patient patient) {
        patientGenericDao.add(patient);
    }

    public List<Patient> getAll() {
        return patientGenericDao.getAll(patient);
    }

    public void viewPatientAppointment(Patient patient) {
        List<Appointment> appointments = appointmentService.getAll();

        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getId() == patient.getId()) {
                System.out.println(appointment);
            }
        }
    }

    public List<Appointment> viewPatientAppointmentAsList(Patient user) {
        List<Appointment> appointments = appointmentService.getAll();
        List<Appointment> returnableAppointments = new ArrayList<Appointment>();

        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getId() == user.getId()) {
                returnableAppointments.add(appointment);
            }
        }
        return returnableAppointments;
    }

    public void viewPatientRecipes(Patient patient) {
        RecipeService recipeService = new RecipeService();
        List<Recipe> recipes = recipeService.getAll();

        for (Recipe recipe : recipes) {
            if (recipe.getAppointment().getPatient().getId() == patient.getId()) {
                System.out.println("Doctor " + recipe.getAppointment().getDoctor().getDoctorName()
                        + " specialization " + recipe.getAppointment().getDoctor().getSpecialization()
                        + " date: "+ recipe.getAppointment().getDateAndTime()
                        + " disease: " + recipe.getDiseases()
                        + " medication: " + recipe.getPrescribed_medication());
            }
        }
    }
}
