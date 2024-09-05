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
public class Factura implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private Long id;

    private String fecha;
    private int numero;
    private int total;

    @ManyToOne(cascade = CascadeType.PERSIST) //Solo vamos a querer un persistir a un cliente (si es que no existe) al persistir la factura, pero si borramos la factura no se borrará el cliente como si sucede con el ALL
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;


//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) //Porque al ser una composición, lo que queremos es que cuando yo elimine una factura, también se eliminen sus detalles o cuando se persistan el mismo ejemplo
//    @JoinColumn(name = "factura_id")
//    @Builder.Default
//    private Set<DetalleFactura> detalleFactura = new HashSet<>();      Todo lo comentado sirve para la relación unidireccional y que no se cree una tabla nueva, solo se cree un campo en detalle factura con el id de factura

    @Builder.Default
      @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true) //Porque al ser una composición, lo que queremos es que cuando yo elimine una factura, también se eliminen sus detalles o cuando se persistan el mismo ejemplo

      private Set<DetalleFactura> detalleFactura = new HashSet<>();
}
