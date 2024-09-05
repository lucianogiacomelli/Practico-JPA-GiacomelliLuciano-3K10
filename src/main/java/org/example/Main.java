package org.example;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("example-unit");

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin(); //Se inicia una transacción

            Factura factura1 = Factura.builder()
                    .numero(12)
                    .fecha("2024-12-31")
                    .build();
            Domicilio dom1 = Domicilio.builder()
                    .nombreCalle("San Martín")
                    .numero(1222)
                    .build();
            Cliente cliente = Cliente.builder()
                    .dni(45257257)
                    .nombre("Luciano")
                    .apellido("Giacomelli")
                    .build();
            cliente.setDomicilio(dom1);
            dom1.setCliente(cliente);
            factura1.setCliente(cliente);

            Categoria perecederos = Categoria.builder()
                    .denominacion("Perecedero")
                    .build();
            Categoria lacteos = Categoria.builder()
                    .denominacion("Lacteos")
                    .build();
            Categoria limpieza = Categoria.builder()
                    .denominacion("Limpieza")
                    .build();
            Articulo art1 = Articulo.builder()
                    .cantidad(200)
                    .denominacion("Yogurt")
                    .precio(20)
                    .build();
            Articulo art2 = Articulo.builder()
                    .cantidad(300)
                    .denominacion("Detergente")
                    .precio(80)
                    .build();
            art1.getCategoria().add(perecederos);
            art1.getCategoria().add(lacteos);
            art2.getCategoria().add(limpieza);
            perecederos.getArticulo().add(art1);
            lacteos.getArticulo().add(art1);
            limpieza.getArticulo().add(art2);

            DetalleFactura det1 = DetalleFactura.builder().build();
            det1.setArticulo(art1);
            det1.setCantidad(2);
            det1.setSubtotal(40);

            art1.getDetalleFacturas().add(det1);
            factura1.getDetalleFactura().add(det1);
            det1.setFactura(factura1);

            DetalleFactura det2 = DetalleFactura.builder().build();

            det2.setArticulo(art2);
            det2.setCantidad(1);
            det2.setSubtotal(80);
            art2.getDetalleFacturas().add(det2);
            factura1.getDetalleFactura().add(det2);
            det2.setFactura(factura1);


            factura1.setTotal(120);

            em.persist(factura1);
//            Cliente cliente1 = Cliente.builder()
//                    .nombre("Luciano")
//                    .dni(45257283)
//                    .build();
//            Domicilio dom1 = Domicilio.builder()
//                    .nombreCalle("Alberdi")
//                    .numero(59)
//                    .build();
//            cliente1.setDomicilio(dom1);
//            dom1.setCliente(cliente1);
//
//            em.persist(cliente1); //Sirve para persistir en la base de datos
            em.flush();//Sirve para limpiar la conexión
            em.getTransaction().commit();//Sirve para terminar la persistencia
        } catch (Exception e){
            System.out.println("Error");
            em.getTransaction().rollback();//Sirve para la atomicidad de las transacciones
        }

        em.close();
        emf.close();
    }
}