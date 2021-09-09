package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Appointment {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;


    private LocalDateTime dateAndTime;

    public Appointment(Patient patient, Doctor doctor, LocalDateTime dateAndTime) {
        this.patient = patient;
        this.doctor = doctor;
        this.dateAndTime = dateAndTime;
    }


    public Appointment() {

    }
}
