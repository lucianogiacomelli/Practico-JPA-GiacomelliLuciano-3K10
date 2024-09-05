package org.example;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity

public class Domicilio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String nombreCalle;
    private int numero;

    @OneToOne(mappedBy = "domicilio") //Sirve para indicar quien es el dueño de la relación

    private Cliente cliente;
}
