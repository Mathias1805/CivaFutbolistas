package Api.Futbolistas.Civa.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posicion")
public class PosicionEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "IdPosicion")
    private Integer IdPosicion;
    @Column(name = "Posicion")
    private String Posicion;
    @JsonIgnore
    @OneToMany(mappedBy = "Posicion")
    List<FutbolistaEntity> futbolistas;
}
