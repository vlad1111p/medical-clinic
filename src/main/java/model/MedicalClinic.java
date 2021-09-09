package model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="Medical_Clinic")
public class MedicalClinic {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clinicName;

    private String adrress;

    private String phone;

    private String email;

    @OneToMany(mappedBy="medicalclinic", cascade=CascadeType.ALL,orphanRemoval = true)
    private Set<Patient> patient;

    public MedicalClinic(String clinicName, String adrress, String phone, String email) {
        this.clinicName = clinicName;
        this.adrress = adrress;
        this.phone = phone;
        this.email = email;
    }

    public MedicalClinic() {
    }
}