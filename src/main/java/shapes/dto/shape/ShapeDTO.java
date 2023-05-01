package shapes.dto.shape;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShapeDTO {

    private int shapeId;
    private Date created;
    private Date updated;
    private boolean deleted;
}
