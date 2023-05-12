package shapes.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import shapes.models.dto.action.MoveDTO;
import shapes.models.dto.action.RollDTO;
import shapes.models.dto.action.ScaleDTO;
import shapes.models.dto.point.CreatePointDTO;
import shapes.models.dto.point.PointDTO;
import shapes.models.dto.point.UpdatePointDTO;
import shapes.models.dto.radiusinfo.CreateRadiusInfoDTO;
import shapes.models.dto.radiusinfo.UpdateRadiusInfoDTO;
import shapes.models.dto.shape.CreateShapeDTO;
import shapes.models.dto.shape.ShapeDTO;
import shapes.models.dto.shape.UpdateShapeDTO;
import shapes.models.dto.shapetype.ShapeTypeForShapeDTO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(scripts = "/sql/create-shapetype.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/create-shapes.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/delete-shapes.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = "/sql/delete-shapetype.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ShapesControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ShapesController controller;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void controllerIsNotNull() {
        assertNotNull(controller);
    }

    @Test
    public void getAllTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/shapes/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString(UTF_8);
        List<ShapeDTO> shapes = Arrays.asList(mapper.readValue(body, ShapeDTO[].class));
        assertEquals(3, shapes.size());
        assertEquals(1L, shapes.get(0).getShapeId());
        assertEquals(1L, shapes.get(0).getShapeType().getShapeTypeId());
        assertEquals(2L, shapes.get(1).getShapeId());
        assertEquals(2L, shapes.get(1).getShapeType().getShapeTypeId());
        assertEquals(3L, shapes.get(2).getShapeId());
        assertEquals(3L, shapes.get(2).getShapeType().getShapeTypeId());
    }

    @Test
    public void getByIDTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/shapes/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString(UTF_8);
        ShapeDTO shapeDTO = mapper.readValue(body, ShapeDTO.class);
        assertEquals(1L, shapeDTO.getShapeId());
        assertEquals(1L, shapeDTO.getShapeType().getShapeTypeId());
        assertEquals(1, shapeDTO.getPoints().size());
        assertEquals(1L, shapeDTO.getPoints().get(0).getPointId());
        assertEquals(0.0, shapeDTO.getPoints().get(0).getX());
        assertEquals(0.0, shapeDTO.getPoints().get(0).getY());
        assertEquals(1L, shapeDTO.getRadiusInfo().getRadiusInfoId());
        assertEquals(1.0, shapeDTO.getRadiusInfo().getRadius());
    }

    @Test
    public void failCreateShapeTest() throws Exception {
        CreateShapeDTO dto = new CreateShapeDTO();
        String json = mapper.writeValueAsString(dto);
        MockHttpServletRequestBuilder post = post("/shapes")
                .contentType("application/json")
                .content(json);
        MvcResult mvcResult = mockMvc.perform(post)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
        String body = mvcResult.getResponse().getContentAsString(UTF_8);
        String errorMessages = mapper.readTree(body).get("errorMessages").toString();
        assertTrue(errorMessages.contains("{\"errormessage\":\"Атрибут shapeType (тип фигуры) обязателен для заполнения\"}"));
        assertTrue(errorMessages.contains("{\"errormessage\":\"Атрибут points (точки фигуры) обязателен для заполнения\"}"));
    }

    @Test
    public void validationFailCreateShapeTest() throws Exception {
        ShapeTypeForShapeDTO shapeTypeForShapeDTO = new ShapeTypeForShapeDTO(2L);
        CreateRadiusInfoDTO radius_infoDTO = new CreateRadiusInfoDTO(10.0);
        List<CreatePointDTO> pointsDTO = Collections.singletonList(new CreatePointDTO(1.0, 1.0));
        CreateShapeDTO dto = new CreateShapeDTO(shapeTypeForShapeDTO, pointsDTO, radius_infoDTO);
        String json = mapper.writeValueAsString(dto);
        MockHttpServletRequestBuilder post = post("/shapes")
                .contentType("application/json")
                .content(json);
        MvcResult mvcResult = mockMvc.perform(post)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
        String body = mvcResult.getResponse().getContentAsString(UTF_8);
        String errorMessage = mapper.readTree(body).get("message").asText();
        assertEquals("Количество точек [1] переданных в json не соответствует выбранному типу фигуры [треугольник]", errorMessage);
    }

    @Test
    public void successfulCreateShapeTest() throws Exception {
        ShapeTypeForShapeDTO shapeTypeForShapeDTO = new ShapeTypeForShapeDTO(1L);
        CreateRadiusInfoDTO radius_infoDTO = new CreateRadiusInfoDTO(10.0);
        List<CreatePointDTO> pointsDTO = Collections.singletonList(new CreatePointDTO(1.0, 1.0));
        CreateShapeDTO dto = new CreateShapeDTO(shapeTypeForShapeDTO, pointsDTO, radius_infoDTO);
        String json = mapper.writeValueAsString(dto);
        MockHttpServletRequestBuilder post = post("/shapes")
                .contentType("application/json")
                .content(json);
        mockMvc.perform(post)
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void failUpdateShapeTest() throws Exception {
        UpdateShapeDTO dto = new UpdateShapeDTO();
        String json = mapper.writeValueAsString(dto);
        MockHttpServletRequestBuilder put = put("/shapes")
                .contentType("application/json")
                .content(json);
        MvcResult mvcResult = mockMvc.perform(put)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
        String body = mvcResult.getResponse().getContentAsString(UTF_8);
        String errorMessages = mapper.readTree(body).get("errorMessages").toString();
        assertTrue(errorMessages.contains("{\"errormessage\":\"Атрибут shapeId (id фигуры в базе данных) обязателен для заполнения\"}"));
    }

    @Test
    public void validationFailUpdateShapeTest() throws Exception {
        ShapeTypeForShapeDTO shapeTypeForShapeDTO = new ShapeTypeForShapeDTO(2L);
        UpdateRadiusInfoDTO radius_infoDTO = new UpdateRadiusInfoDTO(1L, 10.0);
        List<UpdatePointDTO> pointsDTO = Collections.singletonList(new UpdatePointDTO(1L, 1.0, 1.0));
        UpdateShapeDTO dto = new UpdateShapeDTO(1L, shapeTypeForShapeDTO, pointsDTO, radius_infoDTO);
        String json = mapper.writeValueAsString(dto);
        MockHttpServletRequestBuilder put = put("/shapes")
                .contentType("application/json")
                .content(json);
        MvcResult mvcResult = mockMvc.perform(put)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
        String body = mvcResult.getResponse().getContentAsString(UTF_8);
        String errorMessage = mapper.readTree(body).get("message").asText();
        assertEquals("Количество точек [1] переданных в json не соответствует выбранному типу фигуры [треугольник]", errorMessage);
    }

    @Test
    public void successfulUpdateShapeTest() throws Exception {
        ShapeTypeForShapeDTO shapeTypeForShapeDTO = new ShapeTypeForShapeDTO(1L);
        UpdateRadiusInfoDTO radius_infoDTO = new UpdateRadiusInfoDTO(1L, 10.0);
        List<UpdatePointDTO> pointsDTO = Collections.singletonList(new UpdatePointDTO(1L, 1.0, 1.0));
        UpdateShapeDTO dto = new UpdateShapeDTO(1L, shapeTypeForShapeDTO, pointsDTO, radius_infoDTO);
        String json = mapper.writeValueAsString(dto);
        MockHttpServletRequestBuilder put = put("/shapes")
                .contentType("application/json")
                .content(json);
        mockMvc.perform(put)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteShapeTest() throws Exception {
        mockMvc.perform(delete("/shapes/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void failDeleteByIdTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/shapes/0"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
        String body = mvcResult.getResponse().getContentAsString(UTF_8);
        JsonNode jsonNode = mapper.readTree(body);
        assertEquals("Фигура с id = 0 не найдена", jsonNode.get("message").asText());
    }

    @Test
    public void calculateAreaTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/shapes/area/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString(UTF_8);
        JsonNode shapeArea = mapper.readTree(body).get("shapeArea");
        assertNotNull(shapeArea);
        assertEquals(4, shapeArea.intValue());
    }

    @Test
    public void rollShapeTest() throws Exception {
        RollDTO dto = new RollDTO(3L, 90.0);
        String json = mapper.writeValueAsString(dto);
        MockHttpServletRequestBuilder post = post("/shapes/roll")
                .contentType("application/json")
                .content(json);
        mockMvc.perform(post)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void moveShapeTest() throws Exception {
        MoveDTO dto = new MoveDTO(3L, 2.0, 2.0);
        String json = mapper.writeValueAsString(dto);
        MockHttpServletRequestBuilder post = post("/shapes/move")
                .contentType("application/json")
                .content(json);
        mockMvc.perform(post)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void scaleShapeTest() throws Exception {
        ScaleDTO dto = new ScaleDTO(3L, 2.0);
        String json = mapper.writeValueAsString(dto);
        MockHttpServletRequestBuilder post = post("/shapes/scale")
                .contentType("application/json")
                .content(json);
        MvcResult mvcResult = mockMvc.perform(post)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        String body = mvcResult.getResponse().getContentAsString(UTF_8);
        List<PointDTO> points = mapper.readValue(body, ShapeDTO.class).getPoints();
        assertEquals(5, points.get(0).getPointId());
        assertEquals(-1.0, points.get(0).getX());
        assertEquals(-1.0, points.get(0).getY());

        assertEquals(6, points.get(1).getPointId());
        assertEquals(-1.0, points.get(1).getX());
        assertEquals(3.0, points.get(1).getY());

        assertEquals(7, points.get(2).getPointId());
        assertEquals(3.0, points.get(2).getX());
        assertEquals(3.0, points.get(2).getY());

        assertEquals(8, points.get(3).getPointId());
        assertEquals(3.0, points.get(3).getX());
        assertEquals(-1.0, points.get(3).getY());
    }
}