package co.edu.unisabana.Notification_Parcial2.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Table
@Entity
@Data
public class cliente {

    @Id
    private Integer id;
    @Column
    private String nombre;
    @Column
    private String correo;
    @Column
    private Integer dinero_disponible;

}
