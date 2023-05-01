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
@Table(name = "radius_info")
public class RadiusInfo implements Serializable {
    @Id
    @Column(name = "radius_id")
    @SequenceGenerator(name = "radiusIdSeq", sequenceName = "radius_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "radiusIdSeq")
    private int radiusInfoId;

    @Column(name = "radius")
    private double radius;

    @JsonIgnore
    @OneToOne()
    @JoinColumn(name = "shape_id")
    private Shape shape;

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
