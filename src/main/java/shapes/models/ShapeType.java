package shapes.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
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

    @Column(name = "systemname")
    private String systemName;

    @Column(name = "name")
    private String name;

    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated")
    private LocalDateTime updated;
}
