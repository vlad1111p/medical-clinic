package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "appointment")
@Getter
@Setter
@Table(name ="appointment")
public class Appointment {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;


    private LocalDate dateAndTime;

    public Appointment(Patient patient, Doctor doctor, LocalDate dateAndTime) {
        this.patient = patient;
        this.doctor = doctor;
        this.dateAndTime = dateAndTime;
    }


    public Appointment() {

    }

    @Override
    public String toString() {
        return "Appointment{" +
                "patient=" + patient +
                ", doctor=" + doctor +
                ", dateAndTime=" + dateAndTime +
                '}';
    }
}
