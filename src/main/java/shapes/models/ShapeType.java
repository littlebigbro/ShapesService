package shapes.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shapetype")
public class ShapeType implements Serializable {
    @Id
    @Column(name = "shapetype_id")
    @SequenceGenerator(name = "shapeTypeIdSeq", sequenceName = "shapetype_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shapeTypeIdSeq")
    private Long shapeTypeId;

    @NotEmpty(message = "Системное наименование должно быть заполнено")
    @Column(name = "systemname", unique = true, nullable = false)
    private String systemName;

    @NotEmpty(message = "Наименование должно быть заполнено")
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated")
    private LocalDateTime updated;

    @NotNull(message = "Количество точек должно быть заполнено")
    @Min(value = 1, message = "Количество точек должно быть больше 0")
    @Column(name = "points_count", unique = true)
    private Integer pointsCount;
}
