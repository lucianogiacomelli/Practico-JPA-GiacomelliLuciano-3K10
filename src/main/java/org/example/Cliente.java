package org.example;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    private String apellido;

 @Column(name = "dni", unique = true)
 private int dni;
 private String nombre;

 @OneToOne(cascade = CascadeType.ALL)
 @JoinColumn(name = "fk_domicilio")
 private Domicilio domicilio;

    @Builder.Default
 @OneToMany(mappedBy = "cliente")
 @JoinColumn(name = "cliente_id")

 private Set<Factura> Facturas = new HashSet<>();

}
