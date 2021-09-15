package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "patient")
@Getter
@Setter
@Table(name="patient")
public class Patient {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientName;

    private String surName;
    @Column(unique = true)
    private String CNP;

    private int age;
    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Appointment> appointmentPatient;


    @ManyToOne
    @JoinColumn(name="medicalclinic_id")
    private MedicalClinic medicalClinic;


    public Patient(String patientName, String surName, String CNP, int age, String email, String password,
                   Set<Appointment> appointmentPatient, MedicalClinic medicalClinic) {
        this.patientName = patientName;
        this.surName = surName;
        this.CNP = CNP;
        this.age = age;
        this.email = email;
        this.password = password;
        this.appointmentPatient = appointmentPatient;
        this.medicalClinic = medicalClinic;
    }

    public Patient() {
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientName='" + patientName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
