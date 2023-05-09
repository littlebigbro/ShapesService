package shapes.mappers;

import org.junit.jupiter.api.Test;
import shapes.models.RadiusInfo;
import shapes.models.dto.radiusinfo.CreateRadiusInfoDTO;
import shapes.models.dto.radiusinfo.RadiusInfoDTO;
import shapes.models.dto.radiusinfo.UpdateRadiusInfoDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static shapes.utils.TestUtils.createRadiusInfo;

public class RadiusInfoMapperTest {
    private final RadiusInfoMapper radiusInfoMapper = RadiusInfoMapper.MAPPER;

    @Test
    public void mapNullRadiusInfoToRadiusInfoDTOTest() {
        RadiusInfoDTO radiusInfoDTO = radiusInfoMapper.mapToRadiusInfoDTO(null);
        assertNull(radiusInfoDTO);
    }

    @Test
    public void mapRadiusInfoToRadiusInfoDTOTest() {
        RadiusInfo radiusInfo = createRadiusInfo(1L, null);
        RadiusInfoDTO radiusInfoDTO = radiusInfoMapper.mapToRadiusInfoDTO(radiusInfo);

        assertEquals(radiusInfo.getRadiusInfoId(), radiusInfoDTO.getRadiusInfoId());
        assertEquals(radiusInfo.getRadius(), radiusInfoDTO.getRadius());
    }

    @Test
    public void mapNullCreateRadiusInfoToRadiusInfoTest() {
        RadiusInfo radiusInfo = radiusInfoMapper.mapToRadiusInfo((CreateRadiusInfoDTO) null);
        assertNull(radiusInfo);
    }

    @Test
    public void mapCreateRadiusInfoToRadiusInfoTest() {
        CreateRadiusInfoDTO dto = new CreateRadiusInfoDTO(1.0);
        RadiusInfo radiusInfo = radiusInfoMapper.mapToRadiusInfo(dto);

        assertEquals(dto.getRadius(), radiusInfo.getRadius());
    }

    @Test
    public void mapNullUpdateRadiusInfoToRadiusInfoTest() {
        RadiusInfo radiusInfo = radiusInfoMapper.mapToRadiusInfo((UpdateRadiusInfoDTO) null);
        assertNull(radiusInfo);
    }


    @Test
    public void mapUpdateRadiusInfoToRadiusInfoTest() {
        UpdateRadiusInfoDTO dto = new UpdateRadiusInfoDTO(1L, 1.0);
        RadiusInfo radiusInfo = radiusInfoMapper.mapToRadiusInfo(dto);

        assertEquals(dto.getRadiusInfoId(), radiusInfo.getRadiusInfoId());
        assertEquals(dto.getRadius(), radiusInfo.getRadius());
    }
}