package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Patient {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String patientName;

    private String surName;

    private String CNP;

    private int age;

    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name="medicalclinic_id", nullable=false ,insertable = false,updatable = false)
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
