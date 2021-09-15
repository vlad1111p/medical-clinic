package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "doctor")
@Setter
@Getter
@Table(name="doctor")
public class Doctor {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String doctorName;

    private String surName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String password;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Appointment> appointmentDoctor;

    @Enumerated(EnumType.STRING)
    private Specialization specialization;

    @ManyToOne
    private MedicalClinic medicalClinic;

    public Doctor(String doctorName, String surName, String email, String password,
                  Set<Appointment> appointmentDoctor, Specialization specialization, MedicalClinic medicalClinic) {
        this.doctorName = doctorName;
        this.surName = surName;
        this.email = email;
        this.password = password;
        this.appointmentDoctor = appointmentDoctor;
        this.specialization = specialization;
        this.medicalClinic = medicalClinic;
    }

    public Doctor() {
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + doctorName + '\'' +
                ", surname='" + surName + '\'' +
                ", specialization=" + specialization;
    }
}
