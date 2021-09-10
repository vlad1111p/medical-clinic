package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "recipe")
@Setter
@Getter
@Table(name="recipe")
public class Recipe {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    private Appointment appointment;

    private String investigation_description;

    private String diseases;

    private String prescribed_medication;

    @Override
    public String toString() {
        return "Recipe{" +
                "investigation_description='" + investigation_description + '\'' +
                ", diseases='" + diseases + '\'' +
                ", prescribed_medication='" + prescribed_medication + '\'' +
                '}';
    }
}
