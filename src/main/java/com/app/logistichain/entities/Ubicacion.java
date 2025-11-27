package com.app.logistichain.entities;
import jakarta.persistence.*; import lombok.*; 
@Entity @Table(name="ubicacions")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Ubicacion {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  @Column(unique=true)
  private String codigo;
  private String pasillo;
  private String estante;
  private String nivel;
  @ManyToOne(optional=false)
  @JoinColumn(name="almacen_id")
  private Almacen almacen;
}
