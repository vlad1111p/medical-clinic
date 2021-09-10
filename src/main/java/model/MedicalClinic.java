package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity(name ="medicalclinic")
@Getter
@Setter
@Table(name="medicalclinic")
public class MedicalClinic {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clinicName;

    private String address;

    private String phone;

    private String email;

    @OneToMany(mappedBy="medicalClinic", cascade=CascadeType.ALL,orphanRemoval = true)
    private Set<Patient> patient;

    @OneToMany(mappedBy="medicalClinic", cascade=CascadeType.ALL,orphanRemoval = true)
    private Set<Doctor> doctor;

    public MedicalClinic(String clinicName, String address, String phone, String email, Set<Patient> patient, Set<Doctor> doctor) {
        this.clinicName = clinicName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.patient = patient;
        this.doctor = doctor;
    }

    public MedicalClinic() {
    }
}
