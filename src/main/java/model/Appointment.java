package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Appointment {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="appointment_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name="doctor_id", nullable=false ,insertable = false,updatable = false)
    private Doctor doctor;

}
