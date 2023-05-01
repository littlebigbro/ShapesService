package shapes.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "point")
public class Point implements Serializable {
    @Id
    @Column(name = "point_id")
    @SequenceGenerator(name = "pointIdSeq", sequenceName = "point_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pointIdSeq")
    private int pointId;

    @Column(name = "x")
    private double x;
    @Column(name = "y")
    private double y;

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

    @Override
    public String toString() {
        return String.format("Точка с id = %s имеет координаты: x = %s, y = %s}", pointId, x, y);
    }
}
