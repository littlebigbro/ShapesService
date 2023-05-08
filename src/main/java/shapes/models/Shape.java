package shapes.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shape")
public class Shape implements Serializable {

    @Id
    @Column(name = "shape_id")
    @SequenceGenerator(name = "shapeIdSeq", sequenceName = "shape_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shapeIdSeq")
    private Long shapeId;

    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated")
    private LocalDateTime updated;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shapetype_id")
    private ShapeType shapeType;

    @Setter(AccessLevel.NONE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shape")
    private List<Point> points;

    @Setter(AccessLevel.NONE)
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "shape")
    private RadiusInfo radiusInfo;

    public void setRadiusInfo(RadiusInfo radiusInfo) {
        if (radiusInfo != null) {
            radiusInfo.setShape(this);
        }
        this.radiusInfo = radiusInfo;
    }

    public void setPoints(List<Point> points) {
        if (points != null) {
            points.forEach(point -> point.setShape(this));
        }
        this.points = points;
    }
}
