package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
public class Doctor {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String doctorName;

    private String surName;

    private String email;

    private String password;

    @OneToMany(mappedBy="doctor", cascade=CascadeType.ALL,orphanRemoval = true)
    private Set<Appointment> appointmentDoctor;

    @Enumerated(EnumType.STRING)
    private Specialization specialization;

    @ManyToOne
    private MedicalClinic medicalClinic;

    public Doctor(String doctorName, String surName, String email, String password, Specialization specialization) {
        this.doctorName = doctorName;
        this.surName = surName;
        this.email = email;
        this.password = password;
        this.specialization = specialization;
    }

    public Doctor() {
    }
}
