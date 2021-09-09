package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Patient {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientName;

    private String surName;

    private String CNP;

    private int age;

    private String email;

    private String password;

    @OneToMany(mappedBy="patient", cascade=CascadeType.ALL,orphanRemoval = true)
    private Set<Appointment> appointmentPatient;



    @ManyToOne

    private MedicalClinic medicalClinic;


    public Patient(String patientName, String surName, String CNP, int age, String email, String password) {
        this.patientName = patientName;
        this.surName = surName;
        this.CNP = CNP;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public Patient() {
    }
}
