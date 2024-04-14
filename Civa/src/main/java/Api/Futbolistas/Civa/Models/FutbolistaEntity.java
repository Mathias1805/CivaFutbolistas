package Api.Futbolistas.Civa.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "futbolista")
public class FutbolistaEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "IdFutbolista")
    private Integer IdFutbolista;
    @Column(name = "Nombre")
    private String Nombre;
    @Column(name = "Apellidos")
    private String Apellidos;
    @Column(name = "FechaNacimiento")
    private Date FechaNacimiento;
    @Column(name = "Caracteristicas")
    private String Caracteristicas;
    @ManyToOne
    @JoinColumn(name = "IdPosicion", referencedColumnName = "IdPosicion", nullable = false)
    private PosicionEntity Posicion;
}
