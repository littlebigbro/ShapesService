package shapes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"x", "y"})
@Entity
@Table(name = "point")
public class Point implements Serializable {

    @Id
    @Column(name = "point_id")
    @SequenceGenerator(name = "pointIdSeq", sequenceName = "point_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pointIdSeq")
    private Long pointId;

    @NotNull(message = "Координата X должна быть заполнена")
    @Column(name = "x")
    private Double x;

    @NotNull(message = "Координата Y должна быть заполнена")
    @Column(name = "y")
    private Double y;

    @JsonIgnore
    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    },
            fetch = FetchType.EAGER)
    @JoinColumn(name = "shape_id")
    private Shape shape;
}
