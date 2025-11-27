package com.app.logistichain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "clientes")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"pedidos"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nombre;
    private String identificadorFiscal;
    private String direccion;
    private String telefono;
    private String email;

    // Cliente 1–N PedidoVenta
    @OneToMany(mappedBy = "cliente")
    private List<PedidoVenta> pedidos = new ArrayList<>();

    public void addPedido(PedidoVenta pv) {
        pedidos.add(pv);
        pv.setCliente(this);
    }

    public void removePedido(PedidoVenta pv) {
        pedidos.remove(pv);
        pv.setCliente(null);
    }
}
