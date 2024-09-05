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
public class Articulo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

 private int cantidad;
 private String denominacion;
 private int precio;

 @OneToMany(mappedBy = "articulo", cascade = CascadeType.PERSIST)
 private Set<DetalleFactura> DetalleFacturas = new HashSet<>();


 @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) //Se realiza esto porque solo voy a necesitar que se persistan las categorías o que se actualicen.
 @JoinTable(name = "CategoriaArticulo",
         joinColumns = @JoinColumn(name = "articulo_id"),
         inverseJoinColumns = @JoinColumn(name = "categoria_id")) //Es el encargado de generar la tabla de relación ManyToMany
 private Set<Categoria> categoria = new HashSet<>();
}
