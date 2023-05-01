package shapes.dto.radiusinfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRadiusInfoDTO {
    private int radiusInfoId;
    private double radius;
}
