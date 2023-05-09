package shapes.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import shapes.models.RadiusInfo;
import shapes.models.dto.radiusinfo.CreateRadiusInfoDTO;
import shapes.models.dto.radiusinfo.RadiusInfoDTO;
import shapes.models.dto.radiusinfo.UpdateRadiusInfoDTO;

@Mapper(componentModel = "spring")
public interface RadiusInfoMapper {
    RadiusInfoMapper MAPPER = Mappers.getMapper(RadiusInfoMapper.class);

    //Out
    RadiusInfoDTO mapToRadiusInfoDTO(RadiusInfo radiusInfo);

    //Create
    RadiusInfo mapToRadiusInfo(CreateRadiusInfoDTO radiusInfoDTO);

    //Update
    RadiusInfo mapToRadiusInfo(UpdateRadiusInfoDTO radiusInfoDTO);
}
