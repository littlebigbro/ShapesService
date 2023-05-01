package shapes.domain;

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
@Table(name = "shapetype")
public class ShapeType implements Serializable {

    @Id
    @Column(name = "shapetype_id")
    @SequenceGenerator(name = "shapeTypeIdSeq", sequenceName = "shapetype_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shapeTypeIdSeq")
    private int shapeTypeId;

    @Column(name = "systemname")
    private String systemName;

    @Column(name = "name")
    private String name;
}
